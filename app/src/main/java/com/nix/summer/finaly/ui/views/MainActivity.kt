package com.nix.summer.finaly.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.nix.summer.finaly.*
import com.nix.summer.finaly.ui.adapters.Contract
import com.nix.summer.finaly.ui.adapters.MainPresenter
import com.nix.summer.finaly.core.entity.Coffee
import com.nix.summer.finaly.core.entity.Ingredients
import com.nix.summer.finaly.core.entity.Response
import com.nix.summer.finaly.core.interactors.*
import com.nix.summer.finaly.data.database.Database
import com.nix.summer.finaly.data.mappers.DatabasePaymentToPaymentMapper
import com.nix.summer.finaly.data.mappers.NetworkPaymentToPaymentMapper
import com.nix.summer.finaly.data.mappers.PaymentToDatabasePaymentMapper
import com.nix.summer.finaly.data.network.Network
import com.nix.summer.finaly.data.repository.FakeActionRepositoryImplementation
import com.nix.summer.finaly.data.repository.PaymentRepositoryImplementation

class MainActivity : AppCompatActivity(), Contract.View {
    private lateinit var infoView: TextView
    private lateinit var milkView: TextView
    private lateinit var waterView: TextView
    private lateinit var coffeeView: TextView
    private lateinit var cupView: TextView

    private lateinit var espressoCost: TextView
    private lateinit var latteCost: TextView
    private lateinit var cappuccinoCost: TextView

    private lateinit var waterFill: EditText
    private lateinit var milkFill: EditText
    private lateinit var coffeeFill: EditText
    private lateinit var cupFill: EditText

    private lateinit var espressoButton: ImageButton
    private lateinit var cappuccinoButton: ImageButton
    private lateinit var latteButton: ImageButton
    private lateinit var takeButton: Button
    private lateinit var fillButton: Button

    private lateinit var cartButton: Button

    private lateinit var paymentSwitch: Switch

    private val presenter by lazy {
        val repository = PaymentRepositoryImplementation(
            Network.api,
            NetworkPaymentToPaymentMapper(),
            Database.provideDao(baseContext),
            DatabasePaymentToPaymentMapper(),
            PaymentToDatabasePaymentMapper()
        )

        MainPresenter(
            BuyCoffeeInteractor(FakeActionRepositoryImplementation()),
            TakeMoneyInteractor(FakeActionRepositoryImplementation()),
            FillResourcesInteractor(FakeActionRepositoryImplementation()),
            ExchangeCurrencyInteractor(repository),
            LoadPaymentInteractor(repository)
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attach(this)

        buyEspresso(this)
        buyCappuccino(this)
        buyLatte(this)
        fill(this)
        take(this)
        showCost(this)
        loadPayment(this)

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


    private fun take(view: MainActivity) {
        takeButton = findViewById(R.id.take_btn)
        takeButton.setOnClickListener {
            presenter.getCommand("take", Ingredients())
        }
    }

    override fun showEspressoCost(response: Response) {
        espressoCost = findViewById(R.id.espresso_text_cost)
        espressoCost.text = response.responseString
    }

    override fun showLatteCost(response: Response) {
        latteCost = findViewById(R.id.latte_text_cost)
        latteCost.text = response.responseString
    }

    override fun showCappuccinoCost(response: Response) {
        cappuccinoCost = findViewById(R.id.cappuccino_text_cost)
        cappuccinoCost.text = response.responseString
    }

    private fun showCost(mainActivity: MainActivity) {
        paymentSwitch = findViewById(R.id.money_switch)
        paymentSwitch.setOnClickListener {
            presenter.exchangePayment(checkSwitch())
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

    private fun checkSwitch(): String {
        paymentSwitch = findViewById(R.id.money_switch)
        return if (!paymentSwitch.isChecked) {
            "UAH"
        } else {
            "USD"
        }
    }

    override fun showPayment(response: Response) {
        infoView = findViewById(R.id.info_text)
        infoView.text = response.responseString
    }

    fun loadPayment(view: MainActivity) {
        cartButton = findViewById(R.id.cart_btn)
        cartButton.setOnClickListener {
            presenter.addPayment()
        }
    }
}