function clearAndCloseAddCustomerDialog(delayed) {

    if (!delayed) {
        clearAddCModal();
    } else {
        setTimeout(clearAddCModal, 1000);
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

function clearAndCloseAddContactChannelDialog(delayed) {
    if (!delayed) {
        clearAddContactChannelModal();
    } else {
        setTimeout(clearAddContactChannelModal, 1000);
    }
}

function clearAddCModal() {
    $('#customer_add_dialog').modal('hide');
    $('#customer_add_form').trigger('reset');

}

function clearAddContactModal() {
    $('#contact_add_dialog').modal('hide');
    $('#contact_add_form').trigger('reset');
    $('#customer_details_dialog').modal('show');
}

function clearAddContactChannelModal() {
    $('#contact_channel_add_dialog').modal('hide');
    $('#contact_channel_add_form').trigger('reset');
    $('#customer_details_dialog').modal('show');
}

// for the projects

function clearAddProjectModal() {
    $('#project_add_dialog').modal('hide');
    $('#project_add_form').trigger('reset');
    $('#customer_details_dialog').modal('show');

}

function clearAndCloseEditProjectDialog(delayed) {

    if (!delayed) {
        $('#project_edit_dialog').modal('hide');
        $('#customer_details_dialog').modal('show');
    } else {
        setTimeout(function () {
            $('#project_edit_dialog').modal('hide');
            $('#customer_details_dialog').modal('show');
        }, 1000);
    }
}

function clearAndCloseAddProjectDialog(delayed) {

    if (!delayed) {
        clearAddProjectModal();
    } else {
        setTimeout(clearAddProjectModal, 1000);
;
    }
}

//for the projects