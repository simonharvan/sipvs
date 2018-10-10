<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Car rent formular</h2>
                <h3>Order ID: <xsl:value-of select="car-rent/@orderid" /></h3>
                <h4>Order person</h4>
                <p>Name: <xsl:value-of select="car-rent/orderperson/person/name"/>
                </p>
                <p>Date of birth: <xsl:value-of select="car-rent/orderperson/person/date-of-birth"/>
                </p>
                <p>ID: <xsl:value-of select="car-rent/orderperson/person/id"/>
                </p>
                <p>Email: <xsl:value-of select="car-rent/orderperson/person/email"/>
                </p>
                <p>Phone number: <xsl:value-of select="car-rent/orderperson/person/phone-number"/>
                </p>
                <h4>Car rented</h4>
                <p>Brand: <xsl:value-of select="car-rent/car/brand"/>
                </p>
                <p>Type: <xsl:value-of select="car-rent/car/type"/>
                </p>
                <p>Model: <xsl:value-of select="car-rent/car/model"/>
                </p>
                <p>Engine: <xsl:value-of select="car-rent/car/engine"/>
                </p>
                <p>Color: <xsl:value-of select="car-rent/car/color"/>
                </p>
                <p>Year: <xsl:value-of select="car-rent/car/year"/>
                </p>
                <p>Year: <xsl:value-of select="car-rent/car/number_of_days"/>
                </p>
                <h4>Price</h4>
                <p><xsl:value-of select="car-rent/car/price"/> €</p>
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
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>

