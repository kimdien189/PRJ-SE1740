const toTopBtn = document.getElementById("to-top-btn");

toTopBtn.addEventListener("click", function() {
  console.log("Button clicked!");
  document.documentElement.scrollTop = 0;
});

window.addEventListener("scroll", function() {
  if (document.documentElement.scrollTop > 200) {
    toTopBtn.style.display = "block";
  } else {
    toTopBtn.style.display = "none";
  }
});