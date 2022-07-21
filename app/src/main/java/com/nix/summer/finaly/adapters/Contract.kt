package com.nix.summer.finaly.adapters

import com.nix.summer.finaly.core.entity.Response

class Contract {

    interface View {
        fun showInfo(response: Response)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
    }
}