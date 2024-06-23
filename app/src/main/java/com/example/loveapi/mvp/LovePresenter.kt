package com.example.loveapi.mvp

class LovePresenter(
    private val view: LoveContract.View,
    private val model: LoveContract.Model
) : LoveContract.Presenter {
    override fun calculateLovePercentage(firstName: String, secondName: String) {
        view.showProgress()
        model.getLovePercentage(firstName, secondName) { result, error ->
            view.hideProgress()
            if (error != null) {
                view.showError(error)
            } else if (result != null) {
                view.showResult(result)
            }
        }
    }
}