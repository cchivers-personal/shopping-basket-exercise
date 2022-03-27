package shoppingcart

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ApplicationTest extends AnyFlatSpec with TypeCheckedTripleEquals with Matchers {
  "Application" should "accept a number of input arguments and output the total price in the expected format (no offers applied)" in {
    val arguments = Array("Apple", "Orange", "Orange")
    val result    = Application.process(arguments)
    result should ===("£1.10")
  }

  it should "accept a number of input arguments and output the total price in the expected format (where offers are applied)" in {
    val arguments = Array("Apple", "Apple", "Orange", "Orange", "Orange")
    val result    = Application.process(arguments)
    result should ===("£1.10")
  }

  it should "accept zero input arguments and output the 0 as the total price" in {
    val arguments = Array.empty[String]
    val result    = Application.process(arguments)
    result should ===("£0.00")
  }

  it should "display a failure message for items that don't exist" in {
    val arguments = Array("Apple", "Banana")
    val result    = Application.process(arguments)
    result should ===("Error: Invalid items as input arguments [Banana]")
  }
}
