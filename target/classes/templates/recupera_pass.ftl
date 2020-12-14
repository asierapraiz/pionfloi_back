<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Sending Email with Freemarker HTML Template Example</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <!-- use the font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 48px;
        }
    </style>
</head>
<body style="margin: 0; padding: 0;">

    <table align="center" border="0" cellpadding="0" cellspacing="0" width="800" style="border-collapse: collapse;border-radius:15px;">
        <tr>
            <td align="center" bgcolor="#6dcbed" style="padding: 40px 0 30px 0;">
                <!--<img src="cid:logo.png" alt="https://memorynotfound.com" style="display: block;" />-->
            </td>
        </tr>
        <tr>
            <td bgcolor="#d9ddde" style="padding: 40px 30px 40px 30px;">
                <p>Querido ${name} ${apellido}</p>
                <p>Este enlace sólo será válido durante la próxima hora.</p>
               <a href="${url}">Cambiar tu password</a>
                <p>Un saludo.</p>
            </td>
        </tr>
      
    </table>

</body>
</html>