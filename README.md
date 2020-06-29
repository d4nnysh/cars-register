# cars-register
Program do ewidencji pojazdów oraz kierowców.

## Uruchomienie
Aby uruchomić program należy upewnić się, że w tym samym folderze znajduje się [sqlite-jdbc-3.7.2.jar](https://livelancsac-my.sharepoint.com/:u:/g/personal/daniszew_lancaster_ac_uk/EeXQ0bwqtRtDvn9pNpkomFUBaH-4TQnvAp9sB6rLTNOjOQ?e=EzHP6C), a następnie wystarczy włączyć run.bat na Windowsie lub run.sh na Linuksie.

**Uwaga** SQLite nie obsługuje wielowątkowości, w związku z tym, czasami może się zdarzyć, że program podczas manipulacji danych, w konsoli wyświetli komunikat "Database is locked". Aby kontynuować korzystanie z aplikacji, zazwyczaj wystarczy ją zrestartować.

## Opis programu
Cars-register to program do ewidencji pojazdów, pozwalający na przechowywanie oraz edytowanie danych samochodów oraz ich właścicieli. Za jego pomocą możemy poznać statystyki pojazdów w bazie danych (na przykład takie jak najstarszy samochód, największy przebieg, czy też średnia liczba samochodów na kierowcę). W pierwszym oknie, poza statystykami, znajdziemy przyciski otwierające menu samochodów oraz menu kierowców.

![Main menu](https://github.com/d4nnysh/cars-register/blob/master/assets/main_window.png?raw=true)

Po ich otwarciu, można zauważyć, że na górze znajduje się wyszukiwarka, przycisk do dodawnia nowych danych oraz przycisk do odświeżania.

![Drivers_menu](https://github.com/d4nnysh/cars-register/blob/master/assets/drivers_window.png?raw=true)

W środkowej części okna znajduje się lista danych - w przypadku kierowców posortowanych po nazwiskach, a w przypadku samochodów, po rejestracjach. Przycisk edytuj po prawej stronie każdego wiersza pozwala na edycję danych konkretnego pojazdu lub właściciela. 

![Cars_menu](https://github.com/d4nnysh/cars-register/blob/master/assets/cars_window.png?raw=true)

W oknie dodawnia kierowcy należy wpisać co najmniej PESEL, który musi być unikatowy oraz zawierać dokładnie 11 cyfr.

![Add_driver](https://github.com/d4nnysh/cars-register/blob/master/assets/add_driver.png?raw=true)

Po dodaniu danych, otworzy się okno edycji, w którym możemy dodawać do kierowcy nieprzypisane samochody, usuwać pojazdy od właściciela (wybierając z rozwijanych list po lewej), a także usuwać dane kierowcy. Po usunięciu relacji lub kierowcy, samochody pozostają jednak w bazie danych, bez przypisanego właściciela.

![Edit driver](https://github.com/d4nnysh/cars-register/blob/master/assets/edit_driver.png?raw=true)

W oknie dodawania samochodu należy wpisać co najmniej rejestrację (która musi być unikatowa i nie dłuższa niż 8 znaków), przebieg oraz rok produkcji.

![Add_car](https://github.com/d4nnysh/cars-register/blob/master/assets/add_car.png?raw=true)

Po dodaniu samochodu, otworzy się okno edycji, w którym możemy też usunąć pojazd. 

![Edit_car](https://github.com/d4nnysh/cars-register/blob/master/assets/edit_car.png?raw=true)
