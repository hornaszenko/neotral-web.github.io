<h1>Komunikator internetowy</h1>
<p>Jest to prosta aplikacja internetowa zbudowana w oparciu o technologie Spring 5 oraz Angular 7 i jednocześnie projekt
zaliczeniowy na przedmiot Programowanie aplikacji internetowych</p>
<p>Elementy aplikacji</p>
<ul>
<li><strong>Integracja z bazą danych H2</strong> - serwer komunikuje się z bazą danych w celu zapisywania i odczytywania danych dotyczących
użytkowników, grup i wiadomości. Cała komunikacja oparta jest na Spring JPA i Spring Data.</li>
<li><strong>Rejestracja nowych użytkowników</strong> - Aby móc korzystać z aplikacji, konieczne jest posiadanie konta. Specjalny formularz
pozwala na podanie danych takich jak nazwa użytkownika, hasło. Dane te są poddawane sprawdzeniu i zapisywane do bazy danych aplikacji.
<li><strong>Autoryzacja użytkownika poprzez JSON Web Token</strong> - Każdy użytkownik logujący się do systemu otrzymuje od serwera
wygenerowany token, będący jednoznacznym identyfikatorem użytkownika. Token taki jest zapisywany w pamięci przeglądarki i dodawany
poprzez interceptor do każdego zapytania do serwera. Serwer z kolei, sprawdza istnieje tego tokenu w nagłówku http, sprawdza jego
poprawność,a także czy okres jego ważności nie minął. Na podstawie tego serwer udostępnia zasoby, bądź odsyła użytkownika do
strony logowania w celu potwierdzenia tożsamości.</li>
<li><strong>REST services</strong> - Warstwa frontowa komunikuje się z serwerem za pomocą kilku dostępnych metod.</li>
<li><strong>Single Page Application</strong> - Dzięki wykorzystaniu routingu warstwa frontowa to jedna strona internetowa w sposób dynamiczny
reagująca na zmianę danych</li>
</ul>
<p>Aplikacja pozwala na prowadzenie konwersacji pomiędzy zalogowanymi użytkownikami poprzez grupy. Użytkownik może zasubskrybować
dowolną grupę, jak również ją opuścić</p>
<img src="https://github.com/piotrkrzyminski/communicator/blob/master/images/communicator-login.jpg"/>
<img src="https://github.com/piotrkrzyminski/communicator/blob/master/images/communicator-register.jpg" alt="Widok ekranu rejestracji"/>
<img src="https://github.com/piotrkrzyminski/communicator/blob/master/images/communicator-home.jpg" alt="Widok strony głównej"/>
<img src="https://github.com/piotrkrzyminski/communicator/blob/master/images/communicator-select-group.jpg" alt="Widok dodawania grup"/>
