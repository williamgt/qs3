# qs3

Frivillig prosjekt 2022Frivillig prosjekt 2022
Prosjektoppgaven er frivillig og kan kun utføres av studenter som får C i mappevurderingen i emnet. Prosjektet gjennomføres av studenter som vil forbedre karakteren i mappevurderingen til A eller B. 

Merk at alle deler av prosjektet (programmert løsning, dokumentasjon og presentasjon) forventes å holde meget god kvalitet for å oppnå karakteren B eller A. Normalt så skal dette altså gjelde ca. 30% av en studentklasse.
Maks antall studenter i en gruppe: 3
Det forventes mer av løsningen jo flere man er på gruppen. Forventet innsats per student er rundt 40 timer.
Prosjektet inneholder flere deler og jo mer gruppen gjør - med god kvalitet - jo bedre. Det skal godt gjøres å rekke over all ønsket funksjonalitet, så sørg for å gjøre en vurdering av hva gruppen prioriterer og gjør funksjonaliteten dere velger mest mulig ferdig.
Vurderingen legger vekt på god kode og nødvendig dokumentasjon for testing og videreutvikling (tenk ala prosjektet i Nettverksprogrammering), men også brukergrensesnitt og godt implementert funksjonalitet er viktig ved vurdering.
 
Løsningen bør inneholde tester og gruppen bør sørge for å bruke CI/CD ved utvikling. OWASP og universell utforming er også begreper som naturlig hører hjemme i prosjektet. Likeså er autentisering/autorisering. 
Oppgave
Årets prosjektoppgave er å lage et alternativ til Qs. Det er ønskelig at spesielt kø og øvingsgodkjenning av øvingslærer er godt tilpasset bruk på mobil. Når det gjelder administrasjonsdelen så er det mindre viktig med mobil-tilpasning der.
Student-og læringsassistentgrensesnitt/funksjonalitet  
Funksjonaliteten som ønskes er stort sett den samme som dere er vant med i Qs.
Systemet må altså for hvert fag inneholde en kø, som studenter kan registrere seg i, for enten å få hjelp eller godkjenne en øving. Godkjenningen bør være strømlinjeformet slik at minst mulig trykk trengs for å bli ferdig. Det er også ønskelig at studentene kan oppgi posisjonen de sitter på og at læringsassistent kan se hvor denne posisjonen er på et kart over angitt rom. Læringsassistentene må kunne plukke fra køen (husk at det er flere læringsassistenter som bruker køa samtidig). Assistentene må derfor kunne se hvem i køa som blir betjent og velge noen av de som ikke blir betjent. Det er også ønskelig å kunne utsette studenter som ikke er klar for hjelp/godkjenning - for så å kunne komme tilbake til dem på et senere tidspunkt.
Studentene bør også få oversikt over hvilke øvinger de har godkjent og markeres om man har nok for det aktuelle faget.
Administrator- og lærergrensesnitt/funksjonalitet
Her ligger funksjonalitet for å administrere fag. Dette vil være å opprette nye fag, arkivere og evt slette fag. Husk at samme fag (fagkode) ofte går flere år etterhverandre.
For å forenkle denne delen så kan dere godt begrense dere til én autorisasjonsrolle (lærer). I en virkelig løsning ville man nok hatt en administratorrolle som har full rettigheter og kan se alle fag, mens lærerrollen kun har tilgang til å se de fagene som den gitte læreren er tilknyttet (samt også ha mulighet til å opprette nye fag). Det kan selvsagt også være naturlig med andre forskjeller.
Ved registrering av fag må det angis fagkode og fagnavn. Det må også registreres lærere, øvingslærere samt studenter på faget. Studenter bør kunne registreres i en batch via (CSV-) fil evt. copy paste fra fil i grensesnittet. Alle studentene på et fag skal altså kunne registreres samtidig av administrator/lærer. Formatet er: Etternavn, fornavn, epost (linjeskift) - så neste student på samme format på linja under. Studenter som ikke allerede ligger i databasen skal opprettes og få beskjed via epost.
Ved oppretting av et fag så må det også angis regler for når studenten har nok øvinger. Den enkelste løsningen her er et antall av totalen (f.eks. 5 av 9), men det er ønskelig å også kunne del inn i undergrupper (f.eks. 2 av øving 1-4, 3 av øving 5-10, samt øving 11 er obligatorisk).
Administrator/lærer bør også kunne manuelt legge til studenter og nye lærere enkeltvis direkte via systemets grensesnitt. Her er det nok lurt å tenke på et eget menyvalg for administrering av brukere. Forøvrig er det ønskelig for lærer å få en samlet oversikt over øvingsgodkjenningene for alle studentene i et fag (med markering på hvilke øvinger som er godkjent for den enkelte, samt markering av de som har nok godkjente øvinger).
Teknologi
Gruppen skal bruke Vue.js på klientsiden. Tjenersiden skal være basert på Spring Boot og enten Spring JDBC eller Spring JPA mot database.
Krav til dokumentasjon
Endepunkter (API) skal dokumenteres (her kan f.eks. Swagger brukes). Merk, det kreves forklaring av hva endepunktene gjør og hva de forskjellige attributtene er. I tillegg skal kode som vanlig dokumenteres (javadoc).
Vi vil også ha systemdokumentasjon, dette vil si dokumentasjon som setter en ny utvikler i stand til å kjapt få kjørt opp prosjektet for testing og videre utvikling (arkitekturskisser/klassediagram). Instrukser for hvordan å kjøre opp prosjektet kan gjerne gjøres som en README-fil, mens annen dokumentasjon bør være som PDF.
Innlevering
Innleveringsfristen er mandag 4. april klokken 23:59. Lever løsningen her som en zip-fil, dvs. enten som et maven-prosjekt, kjørbar jar-fil, eller en docker container. Én innlevering per gruppe. Husk å skrive navnet på alle deltakerne på gruppen.
Presentasjon
Prosjektet presenteres på samme måte som i Nettverksprogrammering. Det settes av 15-20 minutter, med 5-10 minutter satt av til spørsmål fra faglærere. Vi kommer tilbake med alternativer til presentasjonstidspunkter, fortrinnsvis i uke 14.
Spørsmål til oppgaven?
Bruk forumet på BB. Tanken er dog at dette hovedsaklig skal gjelde om ønsket funksjonalitet er tvetydig. Gruppen står i stor grad fritt til å tolke oppgaven selv