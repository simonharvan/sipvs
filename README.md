# sipvs

KTO BY NECHAPAL DOLE UVEDENE VECI VOLAJTE NA 0915 037 045 - banas

- co treba spravit aby to fungovalo
  - na tvorbu UI si nainstalujte javafx scene builder (instalacka je v priecinku dependencies)
  - pridajte si kniznicu JDOM do projektu, je tiez v priecinku dependencies

- pridany textfield pre meno, tlacitko na odoslanie 

- pridal som priecinok dependencies, kde som dal instalacku na javafx scenebuilder a JDOM kniznicu !!! 

- po kliku na tlacitko sa zavola funkcia handle(), ktora natiahne text z textfieldu a posle ho do nizsie popisanej triedy

- pridana trieda XMLOutputter
	- riesi vytvaranie xml pomocou elementov a atributov
	- zatial len copy paste, treba to spravit dynamicky cez nejaky cyklus
	- zoberie argumenty - text z UI a capne to do XML
	- XML presne tvorene podla predlohy
	- <el></el> sa tvori pomocou Element
	- atribut elementu pomocou Attribute (zakomentovane)
 
 ************************************ ČIŽE ************************************
 
 CO MAME: 
  - xml, xds, xsl modely
  - gui s jednym textfieldom pre meno a tlacitkom na spracovanie 
  - triedu XML outputter, ktora zoberie pomocou metody handle v controlleri data z UI a spravi z nich xml
  - je to zatial vzorovo spravene, XML obsahuje personu a passengers
  - zatial jedina vec ktora sa dynamicky taha je orderperson -> person -> name
  - zvysok treba takym istym jednoduchym stylom dorobit
  - http://www.studytrails.com/java/xml/jdom2/java-xml-jdom2-outputter/ tu je info k outputteru
  - http://www.studytrails.com/java/xml/jdom2/java-xml-jdom2-saxbuilder-xsd-validating/ tu je priamo validacia xsd vramci kniznice JDOM
  
 CO TREBA: 
  - pridat textfieldy pre vsetky zvysne policka okrem mena, to je len drag n drop shit, pridat im fx:id a a tak isto ako to ja taham v controller.handle() tahat data a posielat ich do outputtera
  - rovnakym sposobom ako je zatial pridavane meno do XML natiahnut vsetky elementy
  - do XML na zaciatok po verzii a encoding pridat este ten riadok pre stylesheet a zvysne elementy
  
  9.9. 21:10  vystup z konzoly po napisani "fero cervenak" do textfieldu a kliknuti na tlacitko: 
  
  {::nomarkdown}

  <?xml version="1.0" encoding="UTF-8"?>
<car-rent>
  <orderperson>
    <person>
      <name>fero cervenak</name>
      <date-of-birth>09-12-1993</date-of-birth>
      <id>EC123056</id>
      <email>john@smith.com</email>
      <phone-number>+421911099339</phone-number>
    </person>
  </orderperson>
  <passengers>
    <person>
      <name>John Smith</name>
      <date-of-birth>09-12-1993</date-of-birth>
      <id>EC123056</id>
      <email>john@smith.com</email>
      <phone-number>+421911099339</phone-number>
    </person>
  </passengers>
</car-rent>

{:/}
