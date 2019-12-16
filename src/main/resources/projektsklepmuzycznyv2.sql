-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 16 Gru 2019, 18:03
-- Wersja serwera: 10.4.8-MariaDB
-- Wersja PHP: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `projektsklepmuzycznyv2`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `album`
--

CREATE TABLE `album` (
  `album_id` int(11) NOT NULL,
  `gatunek_id` int(11) NOT NULL,
  `zespol_id` int(11) NOT NULL,
  `album_nazwa` varchar(30) COLLATE utf8_polish_ci DEFAULT NULL,
  `album_ilosc` int(11) DEFAULT NULL,
  `album_cena` float DEFAULT NULL,
  `album_opis` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `album_zdjecie_sciezka` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `album`
--

INSERT INTO `album` (`album_id`, `gatunek_id`, `zespol_id`, `album_nazwa`, `album_ilosc`, `album_cena`, `album_opis`, `album_zdjecie_sciezka`) VALUES
(1, 1, 1, 'Abbey Road', 23, 24.99, 'Uważany za najlepszy album wszechczasów.', 'abbey_road.jpg'),
(2, 1, 1, 'Help!', 8, 24.99, 'Piaty album The Beatels wydany 1965 roku.', 'help!.jpg'),
(3, 1, 2, 'The Dark Side Of the Moon', 2, 31.99, 'Drugi po Thrillerze Michaela Jacksona najlepiej sprzedajacy się album w historii muzyki.', 'the_dark_side_of_the_moon.jpg'),
(4, 1, 3, 'Image', 32, 19.99, 'Drugi solowy album studyjny Johna Lennona wydany w 1971r.', 'John-Lennon-Imagine.jpg'),
(6, 1, 4, 'Genesis', 1, 17.99, 'Dwunasty album Genesis.', 'genesis.jpg'),
(7, 8, 5, 'Thriller', 32, 23.99, 'Wydany w 1982 roku sprzedany w ponad 100 mln egzemplarzy.', 'thriller.jpeg'),
(8, 1, 6, 'War', 1, 32.99, 'Trzeci studyjny album irlandzkiego rockowego zespoøu U2 wydany w 1983 roku.', 'u2-war-cd-album.jpg'),
(10, 9, 7, 'Elvis Presley', 11, 32.99, 'Debiutancki album Elvisa Presleya z 1956 roku.', 'elvis_presley_album.jpg'),
(11, 10, 12, '+', 32, 34.99, 'Debiutancki album piosenkarza oraz autora tekstow Eda Sheerana wydany 9 wrzeßnia 2011 roku.', '+.jpg'),
(12, 5, 9, 'Pocztówka z WWA', 45, 35.99, 'Czwarty album studyjny polskiego rapera Taco Haminhwaya.', 'pocztowka-wwa.jpg'),
(13, 8, 8, 'Native', 33, 28.99, 'Trzeci album studynjy amerykańskiej grupy pop-rockowej One Republic.', 'native.jpg'),
(14, 2, 10, 'Toxicity', 5, 32.99, 'Drugi album zespołu z 2001 roku.', 'toxicity.jpg'),
(15, 8, 13, 'Ten Summoner\'s Tales', 12, 28.99, 'Solowy album Stinga wydany w 1993 roku.', 'ten-summoners-tales.jpeg');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `gatunek_muzyki`
--

CREATE TABLE `gatunek_muzyki` (
  `gatunek_id` int(11) NOT NULL,
  `gatunek_nazwa` varchar(25) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `gatunek_muzyki`
--

INSERT INTO `gatunek_muzyki` (`gatunek_id`, `gatunek_nazwa`) VALUES
(1, 'Rock'),
(2, 'Metal'),
(3, 'Muzyka elektroniczna'),
(4, 'Muzyka filmowa'),
(5, 'Rap'),
(6, 'Country'),
(7, 'Disco polo'),
(8, 'Pop'),
(9, 'Rock and Roll'),
(10, 'Folk');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klient`
--

CREATE TABLE `klient` (
  `klient_id` int(11) NOT NULL,
  `klient_imie` varchar(20) COLLATE utf8_polish_ci DEFAULT NULL,
  `klient_nazwisko` varchar(20) COLLATE utf8_polish_ci DEFAULT NULL,
  `klient_login` varchar(20) COLLATE utf8_polish_ci NOT NULL,
  `klient_haslo` varchar(60) COLLATE utf8_polish_ci NOT NULL,
  `klient_email` varchar(30) COLLATE utf8_polish_ci DEFAULT NULL,
  `klient_miasto` varchar(20) COLLATE utf8_polish_ci DEFAULT NULL,
  `klient_ulica` varchar(20) COLLATE utf8_polish_ci DEFAULT NULL,
  `klient_nr_domu` int(11) DEFAULT NULL,
  `klient_nr_mieszkania` int(11) DEFAULT NULL,
  `klient_kod_pocztowy` varchar(6) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `klient`
--

INSERT INTO `klient` (`klient_id`, `klient_imie`, `klient_nazwisko`, `klient_login`, `klient_haslo`, `klient_email`, `klient_miasto`, `klient_ulica`, `klient_nr_domu`, `klient_nr_mieszkania`, `klient_kod_pocztowy`) VALUES
(1, 'Jan', 'Kowalski', 'janek', '$2a$10$XtBsdNobphxOkrkdG41CbuOayEm7fpOld/sqpvlNFwZHS5cT1SKuq', 'janek2000@gmail.com', 'Wrocław', 'Wąska', 5, 10, '33-202'),
(2, 'Andrzej', 'Musiał', 'musial13', 'musimusi22', 'andrzej.musi@gmail.com', 'Koszalin', 'Szeroka', 32, 2, '72-331'),
(3, 'Marek', 'Mostowiak', 'mrocznymarek', 'alinamalina22', 'mostek@o2.pl', 'Białogard', 'Niska', 33, NULL, '72-203'),
(4, 'Michalina', 'Tkacz', 'rikitiki11', 'tkarka55', 'mitka@amorki.pl', 'Mielno', 'Nadmorska', 2, 3, '71-132'),
(5, 'Anna', 'Mikita', 'mikitka123', 'kotek4321', 'anulam@yahoo.com', 'Warszawa', 'Miejska', 22, 45, '01-455'),
(6, 'Karolina', 'Polka', 'karolinkaa331', 'karolcia45', 'polka.k@gmail.com', 'Kraków', 'Wielka', 33, NULL, '05-112'),
(8, 'Kamil', 'Nowak', 'kamil', '$2a$10$pDHjjDKR.hkNeclAkTkuP.l/qZX6OzjvbTp7xh9S1oJJR3fLIBU0y', 'kamil@gmai.com', 'Koszalin', 'Jana z Kolna', 24, 8, '75-204'),
(9, 'Szymon', 'Zasimowski', 'wujeksok', '$2a$10$e/A5WgxB4hj/Ojp2DHesi.upndMA.OtEtpkMwfc/DVqn/2C.qkLym', 'wujeksok@gmail.com', 'Koszalin', 'Jana pawła 2', 43, 321, '74-201');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `komentarz`
--

CREATE TABLE `komentarz` (
  `komentarz_id` int(11) NOT NULL,
  `album_id` int(11) NOT NULL,
  `klient_id` int(11) NOT NULL,
  `komentarz_tresc` varchar(1000) COLLATE utf8_polish_ci NOT NULL,
  `komentarz_ocena` int(11) DEFAULT NULL,
  `komentarz_data` datetime DEFAULT NULL,
  `album_album_id` int(11) DEFAULT NULL,
  `klient_klient_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `komentarz`
--

INSERT INTO `komentarz` (`komentarz_id`, `album_id`, `klient_id`, `komentarz_tresc`, `komentarz_ocena`, `komentarz_data`, `album_album_id`, `klient_klient_id`) VALUES
(1, 11, 1, 'aaaaa', 1, '2019-12-04 00:00:00', 11, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pracownik`
--

CREATE TABLE `pracownik` (
  `pracownik_id` int(11) NOT NULL,
  `pracownik_imie` varchar(20) COLLATE utf8_polish_ci DEFAULT NULL,
  `pracownik_nazwisko` varchar(20) COLLATE utf8_polish_ci DEFAULT NULL,
  `pracownik_poziom_uprawnien` int(11) DEFAULT NULL,
  `pracownik_kod` varchar(20) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `pracownik`
--

INSERT INTO `pracownik` (`pracownik_id`, `pracownik_imie`, `pracownik_nazwisko`, `pracownik_poziom_uprawnien`, `pracownik_kod`) VALUES
(1, 'Filip', 'Sobański', 2, 'aezakmi'),
(2, 'Adam', 'Małysz', 1, ''),
(3, 'Mikołaj', 'Święty', 1, ''),
(4, 'Szymon', 'Zasimowski', 2, 'sokmajkok');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `szczegoly_zamowienia`
--

CREATE TABLE `szczegoly_zamowienia` (
  `zamowienie_id` int(11) NOT NULL,
  `album_id` int(11) NOT NULL,
  `szcze_zam_cena_jednostki` float DEFAULT NULL,
  `szcze_zam_ilosc` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `szczegoly_zamowienia`
--

INSERT INTO `szczegoly_zamowienia` (`zamowienie_id`, `album_id`, `szcze_zam_cena_jednostki`, `szcze_zam_ilosc`) VALUES
(1, 1, NULL, 1),
(1, 11, NULL, 3),
(2, 10, NULL, 1),
(2, 15, NULL, 1),
(8, 11, 23.99, 2),
(11, 1, 24.99, 3),
(11, 2, 24.99, 1),
(11, 3, 31.99, 1),
(12, 2, 24.99, 5),
(12, 4, 19.99, 3),
(12, 6, 17.99, 1),
(12, 7, 23.99, 1),
(13, 1, 24.99, 1),
(13, 2, 24.99, 1),
(13, 6, 17.99, 1),
(14, 1, 24.99, 1),
(14, 2, 24.99, 1),
(14, 7, 23.99, 1),
(14, 11, 34.99, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `utwor`
--

CREATE TABLE `utwor` (
  `utwor_id` int(11) NOT NULL,
  `album_id` int(11) NOT NULL,
  `utwor_nazwa` varchar(40) COLLATE utf8_polish_ci NOT NULL,
  `utwor_czas_trwania` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zamowienie`
--

CREATE TABLE `zamowienie` (
  `zamowienie_id` int(11) NOT NULL,
  `pracownik_id` int(11) NOT NULL,
  `klient_id` int(11) NOT NULL,
  `zamowienie_wartosc` float DEFAULT NULL,
  `zamowienie_komentarz` varchar(1000) COLLATE utf8_polish_ci DEFAULT NULL,
  `zamowienie_data` datetime DEFAULT NULL,
  `zamowienie_status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `zamowienie`
--

INSERT INTO `zamowienie` (`zamowienie_id`, `pracownik_id`, `klient_id`, `zamowienie_wartosc`, `zamowienie_komentarz`, `zamowienie_data`, `zamowienie_status`) VALUES
(1, 1, 1, 21.37, 'Proszę o szybką dostawę.', '2019-10-15 17:31:22', 1),
(2, 1, 2, 10.2, 'W przypadku braku tego towaru proszę o kontakt.', '2019-10-17 13:21:59', 1),
(3, 3, 3, 19.03, NULL, '2019-10-19 04:21:19', 0),
(4, 2, 3, 54.4, NULL, '2019-11-03 09:19:26', 2),
(5, 1, 4, 16.7, NULL, '2019-10-21 07:27:21', 1),
(6, 1, 3, 15.8, 'Mieszkanie na drugim piętrze, domofon nie działa.', '2019-10-25 18:14:29', 0),
(7, 3, 4, 66.88, NULL, '2019-11-26 08:21:16', 1),
(8, 1, 8, 66.88, 'Komentarz', '2019-12-02 04:17:20', 0),
(11, 1, 8, 131.95, 'Prosze szybko', '2019-12-16 01:38:53', 1),
(12, 1, 8, 226.9, 'Poprosze o kontakt.', '2019-12-16 10:35:20', 2),
(13, 1, 8, 67.97, 'Jeszcze troszke chcialbym zamowic', '2019-12-16 10:42:22', 0),
(14, 1, 8, 108.96, 'Poprosze o kontakt', '2019-12-16 10:54:52', 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zespol`
--

CREATE TABLE `zespol` (
  `zespol_id` int(11) NOT NULL,
  `zespol_nazwa` varchar(20) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `zespol`
--

INSERT INTO `zespol` (`zespol_id`, `zespol_nazwa`) VALUES
(1, 'The Beatels'),
(2, 'Pink Floyd'),
(3, 'John Lennon'),
(4, 'Genesis'),
(5, 'Michael Jackson'),
(6, 'U2'),
(7, 'Elvis Presley'),
(8, 'One Republic'),
(9, 'Taco Hamingway'),
(10, 'System of a down'),
(11, 'Hans Zimmer'),
(12, 'Ed Sheeran'),
(13, 'Sting');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `album`
--
ALTER TABLE `album`
  ADD PRIMARY KEY (`album_id`),
  ADD KEY `FK_ALBUM_GATUNEK_A_GATUNEK_` (`gatunek_id`),
  ADD KEY `FK_ALBUM_ZESPOL_TO_ZESPOL` (`zespol_id`);

--
-- Indeksy dla tabeli `gatunek_muzyki`
--
ALTER TABLE `gatunek_muzyki`
  ADD PRIMARY KEY (`gatunek_id`);

--
-- Indeksy dla tabeli `klient`
--
ALTER TABLE `klient`
  ADD PRIMARY KEY (`klient_id`);

--
-- Indeksy dla tabeli `komentarz`
--
ALTER TABLE `komentarz`
  ADD PRIMARY KEY (`komentarz_id`),
  ADD KEY `FK_KOMENTAR_KLIENT_KO_KLIENT` (`klient_id`),
  ADD KEY `FK_KOMENTAR_TOWAR_KOM_ALBUM` (`album_id`),
  ADD KEY `FK5991h3217gyb5bwro7e14whyu` (`album_album_id`),
  ADD KEY `FKqdsec0mxy5p5go0pg0rrs0wt3` (`klient_klient_id`);

--
-- Indeksy dla tabeli `pracownik`
--
ALTER TABLE `pracownik`
  ADD PRIMARY KEY (`pracownik_id`);

--
-- Indeksy dla tabeli `szczegoly_zamowienia`
--
ALTER TABLE `szczegoly_zamowienia`
  ADD PRIMARY KEY (`zamowienie_id`,`album_id`),
  ADD KEY `FK_SZCZEGOL_SZCZEGOLY_ALBUM` (`album_id`);

--
-- Indeksy dla tabeli `utwor`
--
ALTER TABLE `utwor`
  ADD PRIMARY KEY (`utwor_id`),
  ADD KEY `FK_UTWOR_ALBUM_UTW_ALBUM` (`album_id`);

--
-- Indeksy dla tabeli `zamowienie`
--
ALTER TABLE `zamowienie`
  ADD PRIMARY KEY (`zamowienie_id`),
  ADD KEY `FK_ZAMOWIEN_KLIENT_ZA_KLIENT` (`klient_id`),
  ADD KEY `FK_ZAMOWIEN_PRACOWNIK_PRACOWNI` (`pracownik_id`);

--
-- Indeksy dla tabeli `zespol`
--
ALTER TABLE `zespol`
  ADD PRIMARY KEY (`zespol_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `album`
--
ALTER TABLE `album`
  MODIFY `album_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT dla tabeli `gatunek_muzyki`
--
ALTER TABLE `gatunek_muzyki`
  MODIFY `gatunek_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT dla tabeli `klient`
--
ALTER TABLE `klient`
  MODIFY `klient_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT dla tabeli `komentarz`
--
ALTER TABLE `komentarz`
  MODIFY `komentarz_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `pracownik`
--
ALTER TABLE `pracownik`
  MODIFY `pracownik_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT dla tabeli `utwor`
--
ALTER TABLE `utwor`
  MODIFY `utwor_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `zamowienie`
--
ALTER TABLE `zamowienie`
  MODIFY `zamowienie_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT dla tabeli `zespol`
--
ALTER TABLE `zespol`
  MODIFY `zespol_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `album`
--
ALTER TABLE `album`
  ADD CONSTRAINT `FK_ALBUM_GATUNEK_A_GATUNEK_` FOREIGN KEY (`gatunek_id`) REFERENCES `gatunek_muzyki` (`gatunek_id`),
  ADD CONSTRAINT `FK_ALBUM_ZESPOL_TO_ZESPOL` FOREIGN KEY (`zespol_id`) REFERENCES `zespol` (`zespol_id`);

--
-- Ograniczenia dla tabeli `komentarz`
--
ALTER TABLE `komentarz`
  ADD CONSTRAINT `FK5991h3217gyb5bwro7e14whyu` FOREIGN KEY (`album_album_id`) REFERENCES `album` (`album_id`),
  ADD CONSTRAINT `FK_KOMENTAR_KLIENT_KO_KLIENT` FOREIGN KEY (`klient_id`) REFERENCES `klient` (`klient_id`),
  ADD CONSTRAINT `FK_KOMENTAR_TOWAR_KOM_ALBUM` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`),
  ADD CONSTRAINT `FKqdsec0mxy5p5go0pg0rrs0wt3` FOREIGN KEY (`klient_klient_id`) REFERENCES `klient` (`klient_id`);

--
-- Ograniczenia dla tabeli `szczegoly_zamowienia`
--
ALTER TABLE `szczegoly_zamowienia`
  ADD CONSTRAINT `FK_SZCZEGOL_SZCZEGOLY_ALBUM` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`),
  ADD CONSTRAINT `FK_SZCZEGOL_SZCZEGOLY_ZAMOWIEN` FOREIGN KEY (`zamowienie_id`) REFERENCES `zamowienie` (`zamowienie_id`);

--
-- Ograniczenia dla tabeli `utwor`
--
ALTER TABLE `utwor`
  ADD CONSTRAINT `FK_UTWOR_ALBUM_UTW_ALBUM` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`);

--
-- Ograniczenia dla tabeli `zamowienie`
--
ALTER TABLE `zamowienie`
  ADD CONSTRAINT `FK_ZAMOWIEN_KLIENT_ZA_KLIENT` FOREIGN KEY (`klient_id`) REFERENCES `klient` (`klient_id`),
  ADD CONSTRAINT `FK_ZAMOWIEN_PRACOWNIK_PRACOWNI` FOREIGN KEY (`pracownik_id`) REFERENCES `pracownik` (`pracownik_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
