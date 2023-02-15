package cz.upce.fei.cv01.Controller;

import cz.upce.fei.cv01.Entity.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
YAML, JSON a XML jsou tři různé formáty pro uložení dat. Každý z těchto formátů má své vlastnosti a výhody.

YAML je snadno čitelný a psaný člověkem, což znamená, že je snadnější pro lidi na rozdíl od strojového kódu. Používá odsazení místo uzavíracích závorek.
JSON je také snadno čitelný a psaný strojem, ale vyžaduje uzavírací závorky. JSON se používá pro výměnu dat mezi klientem a serverem.
XML je starší formát, který se používá pro popis strukturovaných dat. XML má složitější syntaxi než YAML a JSON, ale má širší podporu mezi programovacími jazyky.

*/

@RestController
public class HelloController {

        @RequestMapping("/v1")
        public String hello(){
            return "Hello world from Spring Boot application.";
        }


    @RequestMapping("/path/{string}")
    public String getString(@PathVariable String string) {
        return string;
    }

    @GetMapping("/query")
    public String myEndpoint(@RequestParam String query) {
        return "Váš dotaz byl: " + query;
    }

    @GetMapping("/foos")
    @ResponseBody
    public String getFoos(@RequestParam String id) {
        return "ID: " + id;
    }

    @GetMapping("/api/entity")
    @ResponseBody
    public String getPersonName(@RequestBody Person person) throws Exception {
        return person.getName();
    }
}
