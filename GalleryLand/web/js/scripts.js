function fetchMoreImages(columnId) {
    // Get the ID of the column view
    var columnView = document.getElementById(columnId);
    // Make an AJAX request to fetch the next set of images for the column
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "gallery", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            var result = xhr.responseText.split(",");
            console.log(result);
            //0 ID
            //1 URL
            //2 name
            //3 creator
            //4 dateCreated
            //5 likes
            //6 tags
            //surround div
            var imageDiv = document.createElement("div");
            imageDiv.className = "image-view";
            //img url
            var image = document.createElement("img");
            image.src = result[1];
            imageDiv.appendChild(image);
            //image name
            var name = document.createElement("p");
            name.className = "image_name";
            name.textContent = result[2];
            imageDiv.appendChild(name);
            //img id info
            var input = document.createElement("input");
            input.type = "hidden";
            input.name = "imageId";
            input.value = result[0].substring(1);
            imageDiv.appendChild(input);
            //like button
            var button = document.createElement("button");
            button.id = "buttonid" + result[0].substring(1);
            button.type = "button";
            button.onclick = function () {
                liked(result[0].substring(1));
            };
            imageDiv.appendChild(button);
            //like icon
            var thumbsUp = document.createElement("i");
            thumbsUp.className = "fa fa-thumbs-up";
            button.appendChild(thumbsUp);
            //like
            var icon = document.createElement("span");
            icon.className = "icon";
            icon.textContent = "Like";
            button.appendChild(icon);
            //like count
            var likeCount = document.createElement("p");
            likeCount.className = "image_like";
            likeCount.id = "likes" + result[0].substring(1);
            likeCount.textContent = result[5].substring(1) + " likes";
            imageDiv.appendChild(likeCount);
            // Add event listener to the imageDiv element
            image.addEventListener('click', function () {
                window.location.href = "imageVisitControl?image_ID=" + result[0].substring(1);
            });
            // Add hover effect to the imageDiv element
            image.addEventListener('mouseover', function () {
                image.style.boxShadow = "0px 0px 10px #ccc";
            });
            image.addEventListener('mouseout', function () {
                image.style.boxShadow = "none";
            });
            columnView.appendChild(imageDiv);
        }
    }
    ;
    xhr.send("columnId" + encodeURIComponent(columnId));
}
window.addEventListener("load", function () {
    for (var i = 0, max = 10; i < max; i++) {
        fetchMoreImages("col1");
        fetchMoreImages("col2");
        fetchMoreImages("col3");
        fetchMoreImages("col4");
        fetchMoreImages("col5");
        fetchMoreImages("col6");
    }
});
window.addEventListener('scroll', function () {
    var scrollTop = window.scrollTop || document.documentElement.scrollTop;
    var scrollHeight = document.documentElement.scrollHeight;
    var clientHeight = document.documentElement.clientHeight;
    console.log("scrolling");
    console.log("scrollTop:", scrollTop, "clientHeight:", clientHeight, "scrollHeight:", scrollHeight);
    if ((scrollTop + clientHeight > scrollHeight*0.8)) {
        console.log("bottom");
        fetchMoreImages("col1");
        fetchMoreImages("col2");
        fetchMoreImages("col3");
        fetchMoreImages("col4");
        fetchMoreImages("col5");
        fetchMoreImages("col6");
    }
});
function liked(imageId) {
    // Get the button element by its ID
    const likeButton = document.getElementById("buttonid" + imageId);
    // Toggle the "active" class on the button
    likeButton.classList.toggle("liked");
    // Update the like count using the likeImage function
    updateLikes(imageId);
}

function updateLikes(imageId) {
    var likeCount = document.getElementById("likes" + imageId);
    var likeid = "likes" + imageId;
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "changeLike", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
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