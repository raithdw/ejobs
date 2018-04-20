<style>
    body{
        background-image: url("http://www.planwallpaper.com/static/images/6909249-black-hd-background.jpg");

    }
    img{
        background-color: whitesmoke;
        width: 20px;
        position: relative;
        bottom: 25px;

    }
    h1{
        color: wheat;
        font-size: 40px;
        font-weight: bold;
        font-family:Georgia
    }

    .logout{

        font-family: Tahoma;
        font-weight: bold;
        text-align: right;
        font-size: 20px;


    }
    .type{
        text-align: center;

    }
    textarea{
        font-weight: bold;
        font-size: larger;
        background-color: beige;
    }

    ul{
        color: white;
        font-size: 30px;
        text-align: left;

    }

    input{
        position: relative;
        bottom: 5px;
        font-weight: bold;
    }


</style>

<%

    HttpSession s = request.getSession();
    Object o = s.getAttribute("userid");
    if(o==null)
    {
        response.sendRedirect("login.html");
    }
%>


<body>

<head>
    <title>Admin</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

</head>

<body>




<div id="sendSomething"></div>
<div class="type">
    <div id="newtodo">

        <textarea rows="4" cols="50" name="title" id="title" placeholder="Enter Job Title..."></textarea>
        <textarea rows="4" cols="50" name="description" id="description" placeholder="Enter Job Description..."></textarea>
        <input type="button" id="add" value="Add Job"  onClick="addNewToDo()"/>
    </div>

    </br>
</div>


<div class="edit">
    <div id="listOfToDo">
        <h1>Job-uri adaugate:</h1>
        <ul>

        </ul>
    </div>
</div>

</br>


</body>

<script>
    // just doing an ajax call
    function loadToDo() {
        $.ajax({
            url: 'tl?action=read'
        }).done(function (response) {
            putToDoInHTML(response.myjobs);
        });
    }

    function putToDoInHTML(todo) {

        var list = document.getElementById('listOfToDo').getElementsByTagName('ul')[0];
        var listHtml = '';

        for (var i = 0; i < todo.length; i++) {
            var task = todo[i];

            var taskHtml =
                '<ul>'+ '<a href="details.jsp?title=' + task.title + '&description='+ task.description + '"target="_blank"style="color: white">'+task.title+'</a>'+'</br>'+'</ul>'
                + "<td><a href='tl?action=delete&id="+task.id+"'style=\"color: white\"><img src=https://cdn3.iconfinder.com/data/icons/line/36/trash_can-512.png title=delete></a></td>";
            listHtml += taskHtml;
        }
        list.innerHTML = listHtml;


    }

    loadToDo();



    function addNewToDo() {          //trimitem data_add
        var title = document.getElementById('title').value;
        var description = document.getElementById('description').value;

        if(title.trim().length > 0) {
            $.ajax({
                url: 'tl?action=write&title=' + title +'&description='+ description
            }).done(function (response) {
                location.href = "index.jsp";
            });
        }
        else {
            var alertDiv = document.createElement("p");
            alertDiv.setAttribute("id", "alertMessage")
            var alertContent = document.createTextNode("You must insert data!");
            alertDiv.appendChild(alertContent);
            var fieldsDiv = document.getElementById("sendSomething");
            fieldsDiv.appendChild(alertDiv);
        }
    }

    function markDone(id) {


        $.ajax({
            url: 'tl?action=markdone&id=' + id
        }).done(function (response) {
            location.href = "index.jsp";
        });


    }
</script>

<div class="logout">
    <a href="logout"style="color: white">Logout</a>
</div>



</body>

</html>
