<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body style="background-color: #eee;font-family:sans-serif">
                <h2 style="text-align:center;width:700px;line-height:50px">Záväzná objednávka ID <xsl:value-of select="car-rent/@orderid" /></h2>
                <table style="border:1px solid #555;width:700px;height:300px">
                        <tbody>
                                <tr><td style="text-align:center"><h3>Zákazík</h3></td></tr>
                                <tr>
                                        <td style="text-align:center">Meno a priezvisko</td><td><xsl:value-of select="car-rent/orderperson/person/name"/></td>
                                        <td style="text-align:center">Email</td><td><xsl:value-of select="car-rent/orderperson/person/email"/></td>
                                </tr>
                                <tr>
                                        <td style="text-align:center">Dátum narodenia</td><td><xsl:value-of select="car-rent/orderperson/person/date-of-birth"/></td>
                                        <td style="text-align:center">Telefónne číslo</td><td><xsl:value-of select="car-rent/orderperson/person/phone-number"/></td>
                                </tr>
                                <tr>
                                        <td style="text-align:center">Číslo OP</td>
                                        <td><xsl:value-of select="car-rent/orderperson/person/id"/></td>
                                </tr>
                                <tr><td style="text-align:center"><h3>Motorové vozidlo</h3></td></tr>
                                <tr>
                                        <td style="text-align:center">Značka</td><td><xsl:value-of select="car-rent/car/brand"/></td>
                                        <td style="text-align:center">Farba</td><td><xsl:value-of select="car-rent/car/color"/></td>
                                </tr>
                                <tr>
                                        <td style="text-align:center">Typ</td><td><xsl:value-of select="car-rent/car/type"/></td>
                                        <td style="text-align:center">Dátum</td><td><xsl:value-of select="car-rent/car/date-of-rent"/></td>
                                </tr>
                                <tr>
                                        <td style="text-align:center">Model</td><td><xsl:value-of select="car-rent/car/model"/></td>
                                        <td style="text-align:center">Počet dní</td><td><xsl:value-of select="car-rent/car/number_of_days"/></td>
                                </tr>
                                <tr>
                                        <td style="text-align:center">Motor</td><td><xsl:value-of select="car-rent/car/engine"/></td>
                                        <td style="text-align:center">Cena</td><td><xsl:value-of select="car-rent/price"/> €</td>
                                </tr>
                        </tbody>
                </table>
                
                <!--
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th style="text-align:left">Name</th>
                        <th style="text-align:left">Date of birth</th>
                        <th style="text-align:left">ID</th>
                        <th style="text-align:left">Email</th>
                        <th style="text-align:left">Phone number</th>
                    </tr>
                    <xsl:for-each select="car-rent/passengers/person">
                        <tr>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="date-of-birth"/></td>
                            <td><xsl:value-of select="id"/></td>
                            <td><xsl:value-of select="email"/></td>
                            <td><xsl:value-of select="phone-number"/></td>
                        </tr>
                    </xsl:for-each>
                </table>-->
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>

