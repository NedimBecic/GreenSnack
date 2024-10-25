package com.example.green_snack

object CategoryData {

    fun getCategories(): List<CategoryItem> {
        return listOf(
            CategoryItem(R.drawable.fruits, "Voće"),
            CategoryItem(R.drawable.hamburger, "Burger"),
            CategoryItem(R.drawable.salad, "Salata"),
            CategoryItem(R.drawable.steak, "Biftek"),
            CategoryItem(R.drawable.pizza, "Pizza"),
            CategoryItem(R.drawable.sushi, "Suši"),
            CategoryItem(R.drawable.pasta, "Pasta"),
            CategoryItem(R.drawable.sandwich, "Sendvič"),
            CategoryItem(R.drawable.kebab, "Kebab"),
            CategoryItem(R.drawable.soup, "Supa"),
            CategoryItem(R.drawable.coffeecup, "Kafa"),
            CategoryItem(R.drawable.cupcake, "Desert")
        )
    }
}
