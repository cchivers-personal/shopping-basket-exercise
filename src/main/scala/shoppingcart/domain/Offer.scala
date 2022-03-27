package shoppingcart.domain

case class Offer(item: Item, purchaseQuantityRequired: Int, pricedAtQuantity: Int)

object Offer {

  def ThreeForTwo(item: Item)      = Offer(item, 3, 2)
  def BuyOneGetOneFree(item: Item) = Offer(item, 2, 1)
}
