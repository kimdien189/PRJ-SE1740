// Get the "To top" button element
var toTopButton = document.getElementById('to-top-btn');

// Add a click event listener to the button
toTopButton.addEventListener("click", backToTop);

function backToTop() {
  document.body.scrollTop = 0;
  document.documentElement.scrollTop = 0;
}
