# Java - prémiová úloha
Extra task from subject PB162 on [FI MUNI](https://www.fi.muni.cz/)

## Description (Czech language only)
Pro vyřešení této úlohy je třeba
- Chápat principy proudového vstupu a výstupu v Javě.

Rozhraní a třídy ze zadání neměňte!
Úloha simuluje jednoduchou třídní knihu, ve které se vedou záznamy o prospěchu studentů.


**Zadání:**

- Upravte třídu Student tak, aby se dva studenti rovnali právě tehdy, když mají stejné křestní jméno a příjmení.
- Vytvořte třídu ClassBookImpl implementující rozhraní ClassBook.
- Dále vyvtořte třídu Main s metodu main(), která provede následující operace:
- Vytvoří instanci třídy ClassBookImpl,
  - Načte testovací soubor test.data (soubor bude v aktuálním adresáři),
  - přidá jednoho studenta, jehož jméno přečte ze standardního vstupu,
  - tomuto studentovi přidá známky, které přečte ze standardního vstupu (oddělené mezerou),
  - vypíše průmernou známku ke každému studentovi v třídní knize,
  - obsah třídní knihy pomocí metody save() zapíše do souboru test-output.data a
  - obsah katalogu pomocí metody save() zapíše na standardní výstup.

Spuštění metody main() může vypadat nějak takto:



        Zadejte křestní jméno studenta: Maxipes
        Zadejte příjmení studenta: Fík
        Zadejte známky studenta (oddělené mezerou): 1 2 3 4
        ----------------------------
        PRŮMĚRNÉ ZNÁMKY
        ----------------------------
        Franta Novák: 3.0
        Oldřich Pláteníček: 2.0
        Maxipes Fik: 2.5
        Hugo Kokoška: 1.5
        ----------------------------
        ZÁLOHA TŘIDNÍ KNIHY
        ----------------------------
        Franta Novák:1 2 3 4 5
        Oldřich Pláteníček:2 2 2 2 2 2 2 2
        Maxipes Fik:1 2 3 4
        Hugo Kokoška:1 2

V posledním kroku se nepozastavujte nad případně poškozeným kódováním diakritiky; předepsané kódování pro ukladání dat metodou save() je utf-8, zatímco Vy nejspíše pracujete ve Windows s kódováním cp-1250 nebo na Linuxové stanici s kódováním iso-8859-2.

K dispozici je testovací třída, která ale netestuje metodu main()!

**Několik rad**

- Metody ClassBook.load() a ClassBook.save() mají při špatném formátu dat nebo při chybě vstupu/výstupu vyvolat výjimku ClassBookException. Při chybě vstupu/výstupu ale vznikne výjimka typu IOException. Proto musíme výjimku IOException zachytit a zareagovat tak, že vygenerujeme požadovanou výjimku ClassBookException. Abychom neztratili informaci o původní příčině problému, využijeme řetězení výjimek.
- Metody ClassBook.load() a ClassBook.save() mají pracovat s kódováním utf-8. Kódování se nastavuje jako druhý paramter konstruktoru InputStreamReader(InputStream in, String charsetName) a OutputStreamWriter(OutputStream in, String charsetName).
- Třída PrintWriter negeneruje IOException! Když dojde k chybě, potichu si ji označí a pokračuje dál. Pokud chcete reagovat na případnou chybu (což se po vás v zadání chce), musíte buď testovat úspěšnost operace metodou checkError(), nebo raději použít pouze třídu Writer (která již IOException normálně generuje).
## License
MIT