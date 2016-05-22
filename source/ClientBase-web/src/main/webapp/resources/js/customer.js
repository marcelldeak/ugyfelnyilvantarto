function clearAndCloseAddCustomerDialog(delayed) {

    if (!delayed) {
        clearAddModal();
    } else {
        setTimeout(clearAddModal, 1000);
    }
}

function clearAndCloseEditCustomerDialog(delayed) {

    if (!delayed) {
        $('#customer_edit_modal').modal('hide');
    } else {
        setTimeout(function () {
            $('#customer_edit_modal').modal('hide');
        }, 1000);
    }
}

function clearAddModal() {
    $('#customer_add_modal').modal('hide');
    $('#c_add_form').trigger('reset');
   
}

