
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Listado de estudiantes</title>
    <link href="css/view.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class='detail'>
    <div class='detail-container'>
        <dl>
            <dt>
                Matricula
            </dt>
            <dd>
                ${vista.matricula}
            </dd>
            <dt>
                Nombre
            </dt>
            <dd>
                ${vista.nombre}
            </dd>
            <dt>
                Apellido
            </dt>
            <dd>
                ${vista.apellido}
            </dd>
            <dt>
                Telefono
            </dt>
            <dd>
                ${vista.telefono}
            </dd>
        </dl>
    </div>
    <div class='detail-nav'>
        <a href="/"> <button class='close'>
            Close
        </button> </a>
    </div>
</div>

</body>
</html>