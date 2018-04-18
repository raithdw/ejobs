<style>
    body{
        background-color:lightblue;
    }
    .edit{
        color:darkgreen;
        font-size: 20px;
        text-align: left;
        font-family: Tahoma;
    }
    .logout{
        text-align: left;
        font-family: Tahoma;
        font-size: 20px;

    }
    .type{
        text-align: center;
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
        <input type="button" id="add" value="Add"  onClick="addNewToDo()"/>
    </div>

    </br>
</div>


<div class="edit">
    <div id="listOfToDo">
        Aici se vor adauga job-urile:
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
                '<ul>' + '<li>' + task.title + '</li>'+'</ul>'
                + "<td> <a href='tl?action=delete&id="+task.id+"'>x</a></td>";
            listHtml += taskHtml;
        }
        list.innerHTML = listHtml;


    }

    loadToDo();



    function addNewToDo() {          //trimitem date
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
    <p>
        <a href="logout">Logout</a>
    </p>
</div>



</body>

</html>
