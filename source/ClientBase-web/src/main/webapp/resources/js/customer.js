function clearAndCloseAddCustomerDialog(delayed) {

    if (!delayed) {
        clearAddModal();
    } else {
        setTimeout(clearAddModal, 1000);
    }
}

function clearAndCloseEditCustomerDialog(delayed) {

    if (!delayed) {
        $('#customer_edit_dialog').modal('hide');
    } else {
        setTimeout(function () {
            $('#customer_edit_dialog').modal('hide');
        }, 1000);
    }

}

function clearAndCloseAddContactDialog(delayed) {

    if (!delayed) {
        clearAddContactModal();
    } else {
        setTimeout(clearAddContactModal, 1000);
    }

}

function clearAndCloseEditContactDialog(delayed) {

    if (!delayed) {
        $('#contact_edit_dialog').modal('hide');
        $('#customer_details_dialog').modal('show');
    } else {
        setTimeout(function () {
            $('#contact_edit_dialog').modal('hide');
            $('#customer_details_dialog').modal('show');
        }, 1000);
    }

}

function clearAddModal() {
    $('#customer_add_dialog').modal('hide');
    $('#customer_add_form').trigger('reset');

}

function clearAddContactModal() {
    $('#contact_add_dialog').modal('hide');
    $('#contact_add_form').trigger('reset');
    $('#customer_details_dialog').modal('show');
}
