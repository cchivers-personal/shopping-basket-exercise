package shoppingcart

import shoppingcart.domain.{Item, Offer, Price}

import scala.math.Integral.Implicits.infixIntegralOps

trait Checkout {
  def calculateTotal(items: List[Item]): Price
}

object Checkout {
  def apply(availableOffers: Set[Offer]): Checkout = new Checkout {
    override def calculateTotal(items: List[Item]): Price = {
      val (offersApplied, itemsWithoutOffers) = applyOffers(items)
      calculateTotalForOffersApplied(offersApplied) + calculateTotalForItemsWithoutOffers(itemsWithoutOffers)
    }

    private def calculateTotalForOffersApplied(offersApplied: List[Offer]): Price =
      Price(offersApplied.map(offer => offer.item.price.value * offer.pricedAtQuantity).sum)

    private def calculateTotalForItemsWithoutOffers(items: List[Item]): Price =
      items.foldLeft(Price(0))((acc, item) => acc + item.price)

    private def applyOffers(items: List[Item]): (List[Offer], List[Item]) = {

      def helper(itemsWithoutOffersApplied: List[Item], offersToBeChecked: List[Offer], offersApplied: List[Offer]): (List[Offer], List[Item]) =
        offersToBeChecked match {
          case Nil             => (offersApplied, itemsWithoutOffersApplied)
          case offer :: others =>
            val (matchingItems, nonMatchingItems)   = itemsWithoutOffersApplied.partition(_ == offer.item)
            val (timesOfferApplied, itemsRemaining) = matchingItems.size /% offer.purchaseQuantityRequired
            helper(
              nonMatchingItems ++ matchingItems.take(itemsRemaining),
              others,
              offersApplied ++ (0 until timesOfferApplied).map(_ => offer)
            )
        }

      helper(items, availableOffers.toList, List.empty)

    }

  }

}
