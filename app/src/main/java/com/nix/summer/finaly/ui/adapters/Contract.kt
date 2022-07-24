package com.nix.summer.finaly.ui.adapters

import com.nix.summer.finaly.core.entity.Response

class Contract {

    interface View {
        fun showInfo(response: Response)
        fun showEspressoCost(response: Response)
        fun showLatteCost(response: Response)
        fun showCappuccinoCost(response: Response)
        fun showPayment(response: Response)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
    }
}