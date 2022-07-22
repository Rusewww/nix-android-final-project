package com.nix.summer.finaly.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.nix.summer.finaly.*
import com.nix.summer.finaly.ui.adapters.Contract
import com.nix.summer.finaly.ui.adapters.MainPresenter
import com.nix.summer.finaly.core.entity.Coffee
import com.nix.summer.finaly.core.entity.Ingredients
import com.nix.summer.finaly.core.entity.Response
import com.nix.summer.finaly.core.interactors.BuyCoffeeInteractor
import com.nix.summer.finaly.core.interactors.FillResourcesInteractor
import com.nix.summer.finaly.core.interactors.TakeMoneyInteractor
import com.nix.summer.finaly.data.repository.FakeActionRepositoryImplementation

class MainActivity : AppCompatActivity(), Contract.View {
    private lateinit var infoView: TextView
    private lateinit var milkView: TextView
    private lateinit var waterView: TextView
    private lateinit var coffeeView: TextView
    private lateinit var cupView: TextView

    private lateinit var waterFill: EditText
    private lateinit var milkFill: EditText
    private lateinit var coffeeFill: EditText
    private lateinit var cupFill: EditText

    lateinit var espressoButton: Button
    lateinit var cappuccinoButton: Button
    lateinit var latteButton: Button
    lateinit var takeButton: Button
    lateinit var fillButton: Button

    private var presenter = MainPresenter(
        BuyCoffeeInteractor(FakeActionRepositoryImplementation()),
        TakeMoneyInteractor(FakeActionRepositoryImplementation()),
        FillResourcesInteractor(FakeActionRepositoryImplementation())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attach(this)

        buyEspresso(this)
        buyCappuccino(this)
        buyLatte(this)
        fill(this)
        take(this)

    }

    private fun buyEspresso(view: MainActivity) {
        espressoButton = findViewById(R.id.espresso_btn)
        espressoButton.setOnClickListener {
            presenter.getCommand("make", Ingredients(), Coffee.ESPRESSO)
        }
    }

    private fun buyLatte(view: MainActivity) {
        latteButton = findViewById(R.id.latte_btn)
        latteButton.setOnClickListener {
            presenter.getCommand("make", Ingredients(), Coffee.LATTE)
        }
    }

    private fun buyCappuccino(view: MainActivity) {
        cappuccinoButton = findViewById(R.id.cappuccino_btn)
        cappuccinoButton.setOnClickListener {
            presenter.getCommand("make", Ingredients(), Coffee.CAPPUCCINO)
        }
    }

    private fun fill(view: MainActivity) {
        fillButton = findViewById(R.id.fill_btn)
        fillButton.setOnClickListener {
            waterFill = findViewById(R.id.water_fill)
            milkFill = findViewById(R.id.milk_fill)
            coffeeFill = findViewById(R.id.coffee_fill)
            cupFill = findViewById(R.id.cup_fill)
            presenter.getCommand(
                "fill",
                Ingredients(
                    waterFill.text.toString().toInt(),
                    milkFill.text.toString().toInt(),
                    coffeeFill.text.toString().toInt(),
                    cupFill.text.toString().toInt()
                )
            )
        }
    }


    fun take(view: MainActivity) {
        takeButton = findViewById(R.id.take_btn)
        takeButton.setOnClickListener {
            presenter.getCommand("take", Ingredients())
        }
    }

    override fun showInfo(response: Response) {
        infoView = findViewById(R.id.info_text)
        infoView.text = response.responseString

        coffeeView = findViewById(R.id.coffee_text)
        var str = response.ingredients.coffee.toString() + "g"
        coffeeView.text = str

        milkView = findViewById(R.id.milk_text)
        str = response.ingredients.milk.toString() + " ml"
        milkView.text = str

        waterView = findViewById(R.id.water_text)
        str = response.ingredients.water.toString() + " ml"
        waterView.text = str

        cupView = findViewById(R.id.cup_text)
        cupView.text = response.ingredients.disposableCups.toString()
    }
}