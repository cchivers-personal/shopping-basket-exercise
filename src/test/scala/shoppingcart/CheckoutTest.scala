package shoppingcart

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import shoppingcart.domain.Item._
import shoppingcart.domain.Price

class CheckoutTest extends AnyFlatSpec with TypeCheckedTripleEquals with Matchers {

  "Checkout" should "calculate the total price for a series of items" in {
    val items = List(Apple, Apple, Orange, Apple)
    Checkout().calculateTotal(items) should ===(Price(205))
  }

  it should "calculate the total price as zero for a empty list of items" in {
    val items = List.empty
    Checkout().calculateTotal(items) should ===(Price(0))
  }

}
