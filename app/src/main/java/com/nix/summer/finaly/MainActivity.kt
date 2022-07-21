package com.nix.summer.finaly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var infoView: TextView
    private lateinit var milkView: TextView
    private lateinit var waterView: TextView
    private lateinit var coffeeView: TextView
    private lateinit var cupView: TextView

    private lateinit var waterFill: EditText
    private lateinit var milkFill: EditText
    private lateinit var coffeeFill: EditText
    private lateinit var cupFill: EditText

    private var controller = Controller(CoffeeMachine(400, 540, 120, 9, 550))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        controller.attachView(this)
        controller.start()
    }

    fun buyEspresso(view: View) {
        controller.makeCoffee(Coffee.ESPRESSO)
    }

    fun buyLatte(view: View) {
        controller.makeCoffee(Coffee.LATTE)
    }

    fun buyCappuccino(view: View) {
        controller.makeCoffee(Coffee.CAPPUCCINO)
    }

    fun fill(view: View) {
        waterFill = findViewById(R.id.water_fill)
        milkFill = findViewById(R.id.milk_fill)
        coffeeFill = findViewById(R.id.coffee_fill)
        cupFill = findViewById(R.id.cup_fill)
        controller.fillRecourses(
            Ingredients(
                waterFill.text.toString().toInt(),
                milkFill.text.toString().toInt(),
                coffeeFill.text.toString().toInt(),
                cupFill.text.toString().toInt()
            )
        )
    }

    fun take(view: View){
        controller.takeMoney()
    }

    fun showInfo(response: Response) {
        infoView = findViewById(R.id.info_text)
        infoView.text = response.responseString
        coffeeView = findViewById(R.id.coffee_text)
        coffeeView.text = response.ingredients.coffee.toString() + "g"
        milkView = findViewById(R.id.milk_text)
        milkView.text = response.ingredients.milk.toString() + " ml"
        waterView = findViewById(R.id.water_text)
        waterView.text = response.ingredients.water.toString() + " ml"
        cupView = findViewById(R.id.cup_text)
        cupView.text = response.ingredients.disposableCups.toString()
    }
}