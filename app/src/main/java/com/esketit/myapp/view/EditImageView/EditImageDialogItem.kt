package com.esketit.myapp.view.EditImageView

class EditImageDialogItem(
    var id: Int,
    var title: String,
    var isRemove: Boolean
)

enum class EditImageDialogItemID(var value: Int){
    ID_CAMERA(0), ID_GALARY(1), ID_REMOVE(2)
}
