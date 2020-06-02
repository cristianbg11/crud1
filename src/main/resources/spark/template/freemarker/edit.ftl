<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <link href="css/stylesheet.css" type="text/css" rel="stylesheet">
</head>
<body>
<header id="header">
    <nav class="links" style="--items: 5;">
        <a href="/">Inicio</a>
        <a href="crear.html">Crear</a>
        <a href="#">Editar</a>
        <a href="#">Eliminar</a>
        <span class="line"></span>
    </nav>
</header>

<div class="signupSection">
    <form action="/actualizar" method="POST" class="signupForm" name="signupform">
        <h2>Estudiante</h2>
        <ul class="noBullet">
            <li>
                <label for="matricula"></label>
                <input type="text" class="inputFields" id="matricula" name="matricula" placeholder="Matricula" value="${edicion.matricula?c}" required/>
            </li>
            <li>
                <input type="hidden" id="id" name="id" value="${id}">
                <label for="nombre"></label>
                <input type="text" class="inputFields" id="nombre" name="nombre" placeholder="Nombre" value="${edicion.nombre}" required/>
            </li>
            <li>
                <label for="apellido"></label>
                <input type="text" class="inputFields" id="apellido" name="apellido" placeholder="Apellido" value="${edicion.apellido}" required/>
            </li>
            <li>
                <label for="telefono"></label>
                <input type="text" class="inputFields" id="telefono" name="telefono" placeholder="Telefono" value="${edicion.telefono}" required/>
            </li>
            <li id="center-btn">
                <input type="submit" id="join-btn" name="crear">
            </li>
        </ul>
    </form>
</div>

</body>
</html>