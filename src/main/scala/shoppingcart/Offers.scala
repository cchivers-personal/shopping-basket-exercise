package shoppingcart

import shoppingcart.domain.Item.{Apple, Orange}
import shoppingcart.domain.Offer

object Offers {
  def load(): Set[Offer] = Set(
    Offer.BuyOneGetOneFree(Apple),
    Offer.ThreeForTwo(Orange)
  )
}
