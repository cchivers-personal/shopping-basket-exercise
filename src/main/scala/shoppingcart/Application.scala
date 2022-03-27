package shoppingcart

import shoppingcart.domain.{InvalidItems, Item, Price}

object Application {

  def process(inputArgs: Array[String]): String = {
    val offers   = Offers.load()
    val checkout = Checkout(offers)
    val result   = validateArguments(inputArgs).map(checkout.calculateTotal)
    formatResult(result)
  }

  private def validateArguments(arguments: Array[String]): Either[domain.Error, List[Item]] = {
    val (invalidItems, validItems) = arguments.toList.partitionMap { itemStr =>
      Item.withNameInsensitiveEither(itemStr).left.map(_.notFoundName)
    }
    if (invalidItems.isEmpty) Right(validItems)
    else Left(InvalidItems(invalidItems))
  }

  private def formatResult(result: Either[domain.Error, Price]): String =
    result.fold(_.errorOutput, _.format)
}
