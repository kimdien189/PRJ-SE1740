
// Get the "To top" button element
var toTopButton = document.getElementById('to-top-btn');

// Add a click event listener to the button
toTopButton.addEventListener("click", backToTop);

function backToTop() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}
document.addEventListener("DOMContentLoaded", function() {
    // Get the login buttons element
    var loginButtons = document.getElementById("login-buttons");

    // Get the value of the account attribute from the hidden input field
    var account = document.getElementById("account").value;

    // Check if the account is authenticated
    if (account !== null && account !== "") {
        // Account is authenticated, hide the login buttons
        loginButtons.style.display = "none";
    }
});