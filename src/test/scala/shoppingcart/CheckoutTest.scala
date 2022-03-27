package shoppingcart

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import shoppingcart.domain.Item._
import shoppingcart.domain.{Offer, Price}

class CheckoutTest extends AnyFlatSpec with TypeCheckedTripleEquals with Matchers {

  "A checkout without offers applied" should "calculate the total price for a number of items" in {
    val items = List(Apple, Apple, Orange, Apple)
    Checkout(Set.empty).calculateTotal(items) should ===(Price(205))
  }

  it should "calculate the total price as zero for a empty list of items" in {
    val items = List.empty
    Checkout(Set.empty).calculateTotal(items) should ===(Price(0))
  }

  "A checkout with offers applied" should "calculate the total price for a number of items where an offer is applied only once" in {
    val items  = List(Apple, Apple)
    val offers = Set(Offer.BuyOneGetOneFree(Apple))
    Checkout(offers).calculateTotal(items) should ===(Price(60))
  }

  it should "calculate the total price for a number of items where the same offer is applied multiple times" in {
    val items  = List(Apple, Apple, Apple, Apple, Apple, Apple)
    val offers = Set(Offer.BuyOneGetOneFree(Apple))
    Checkout(offers).calculateTotal(items) should ===(Price(180))
  }

  it should "calculate the total price for a number of items where more than one offer is applied" in {
    val items  = List(Apple, Apple, Orange, Orange, Orange)
    val offers = Set(Offer.BuyOneGetOneFree(Apple), Offer.ThreeForTwo(Orange))
    Checkout(offers).calculateTotal(items) should ===(Price(110))
  }

  it should "calculate the total price for a number of items where more than one offer is applied and the offers are incomplete" in {
    val items  = List(Apple, Apple, Orange, Orange, Orange, Apple, Orange)
    val offers = Set(Offer.BuyOneGetOneFree(Apple), Offer.ThreeForTwo(Orange))
    Checkout(offers).calculateTotal(items) should ===(Price(195))
  }

  it should "calculate the total price as zero for a empty list of items" in {
    val items  = List.empty
    val offers = Set(Offer.BuyOneGetOneFree(Apple), Offer.ThreeForTwo(Orange))
    Checkout(offers).calculateTotal(items) should ===(Price(0))
  }
}
