function liked(imageId) {
  // Get the button element by its ID
  const likeButton = document.getElementById("buttonid" + imageId);
  // Toggle the "active" class on the button
  likeButton.classList.toggle("liked");
  // Update the like count using the likeImage function
  updateLikes(imageId);
}
function updateLikes(imageId) {
  var likeCount =document.getElementById("likes" + imageId);
  var likeid = "likes" + imageId;
  var xhr = new XMLHttpRequest();
  xhr.open("POST", "changeLike", true);
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  xhr.onreadystatechange = function() {
    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
      // Update the like count using JSP EL
      likeCount.textContent = xhr.responseText + " likes";
    }
  };
  xhr.send("imageId=" + encodeURIComponent(imageId));
}
// Get the "To top" button element
var toTopButton = document.getElementById('to-top-btn');

// Add a click event listener to the button
toTopButton.addEventListener("click", backToTop);

function backToTop() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}
