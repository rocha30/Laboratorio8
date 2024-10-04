package com.example.laboratorio8

class SampleCategories {

    fun getSampleCategories(): List<Category> {
        return listOf(
            Category(
                idCategory = "1",
                strCategory = "Beef",
                strCategoryThumb = "https://www.themealdb.com/images/category/beef.png",
                strCategoryDescription = "Delicious beef recipes."
            ),
            Category(
                idCategory = "2",
                strCategory = "Chicken",
                strCategoryThumb = "https://www.themealdb.com/images/category/chicken.png",
                strCategoryDescription = "Tasty chicken recipes."
            ),
            Category(
                idCategory = "3",
                strCategory = "Pasta",
                strCategoryThumb = "https://www.themealdb.com/images/category/pasta.png",
                strCategoryDescription = "Yummy pasta recipes."
            )
        )
    }

}
