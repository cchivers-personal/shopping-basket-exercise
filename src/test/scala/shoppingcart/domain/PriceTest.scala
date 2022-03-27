package shoppingcart.domain

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PriceTest extends AnyFlatSpec with TypeCheckedTripleEquals with Matchers {
  "Price" should "be formatted correctly" in {
    Price(0).format should ===("£0.00")
    Price(100).format should ===("£1.00")
    Price(199).format should ===("£1.99")
    Price(205).format should ===("£2.05")
  }
}
