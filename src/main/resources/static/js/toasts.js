function createToast(toastContainer, title, message, delay = 5000) {
    // Tạo phần tử toast
    var toast = document.createElement('div');
    toast.classList.add('toast');
    toast.setAttribute('role', 'alert');
    toast.setAttribute('aria-live', 'assertive');
    toast.setAttribute('aria-atomic', 'true');
    toast.setAttribute('data-autohide', 'false');

    // Tạo phần tử toast header
    var toastHeader = document.createElement('div');
    toastHeader.classList.add('toast-header');
    toastHeader.innerHTML = "<i class='fas fa-circle mr-1' style='color: #40b067;font-size: 0.5rem'></i>"
                          + "<strong class='mr-auto'> " + title + "</strong>";

    // Tạo nút đóng toast
    var closeButton = document.createElement('button');
    closeButton.setAttribute('type', 'button');
    closeButton.classList.add('close');
    closeButton.setAttribute('data-dismiss', 'toast');
    closeButton.setAttribute('aria-label', 'Close');
    closeButton.innerHTML = '<span aria-hidden="true">&times;</span>';

    // Tạo phần tử toast body
    var toastBody = document.createElement('div');
    toastBody.classList.add('toast-body');
    toastBody.innerHTML = message;

    // Gắn các phần tử con vào toast
    toastHeader.appendChild(closeButton);
    toast.appendChild(toastHeader);
    toast.appendChild(toastBody);

    // Gắn toast vào container
    toastContainer.appendChild(toast);

    // Hiển thị toast
    $(toast).toast('show');

    // Xóa toast sau khi hiển thị trong một khoảng thời gian (ms)
    setTimeout(function() {
        toastContainer.removeChild(toast);
    }, delay);
}