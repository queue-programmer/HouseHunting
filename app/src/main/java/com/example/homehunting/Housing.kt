package com.example.homehunting

class Housing(var address: String, var type: Type, var price: Int, var ownerForm: String, var amenities: String) {
}

enum class Type() {
        COTTAGE,
        FLAT,
        HOUSE,
        PENTHOUSE,
        ROOM,
        FARM,
        TREEHOUSE,
        BOX
    }

enum class OwnerForm (){
        RENT,
        SELFOWN
}