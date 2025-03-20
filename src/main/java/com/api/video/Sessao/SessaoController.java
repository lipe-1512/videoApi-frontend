package com.api.video.Sessao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sessao")
public class SessaoController {

    private final SessaoService sessaoService;

    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @GetMapping("/logout")
    public ResponseEntity<String> fazerLogout(@RequestParam String chaveSessao) {
        try {
            sessaoService.expirarSessao(chaveSessao);
            return ResponseEntity.ok("Logout realizado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Não foi possível realizar o logout: " + e.getMessage());
        }
    }
}
