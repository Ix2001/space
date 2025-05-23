package kc.alabuga.space.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WhiteListService {

    public WhiteListService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final JdbcTemplate jdbcTemplate;

    public String add(String ip) {
        ip = ip.trim();
        List<String> results = jdbcTemplate.queryForList(
                "SELECT ip FROM whitelisted_ips WHERE ip = ?", String.class, ip
        );
        if (!results.isEmpty()) {
            return "IP " + ip + " уже был добавлен";
        }

        jdbcTemplate.update("INSERT INTO whitelisted_ips (ip) VALUES (?) ON CONFLICT DO NOTHING", ip);
        return "IP добавлен: " + ip;
    }

    public String remove(String ip) {
        ip = ip.trim();
        List<String> results = jdbcTemplate.queryForList(
                "SELECT ip FROM whitelisted_ips WHERE ip = ?", String.class, ip
        );
        if (results.isEmpty()) {
            return "IP " + ip + " не существует";
        }

        jdbcTemplate.update("DELETE FROM whitelisted_ips WHERE ip = ?", ip);
        return "IP удалён: " + ip;
    }

    public List<String> list() {
        return jdbcTemplate.queryForList(
                "SELECT ip FROM whitelisted_ips ORDER BY created_at DESC", String.class
        );
    }

    public String exists(String ip) {
        ip = ip.trim();
        List<String> result = jdbcTemplate.queryForList(
                "SELECT ip FROM whitelisted_ips WHERE ip = ?", String.class, ip
        );

        if (!result.isEmpty()) {
            return "IP адрес " + ip + " существует";
        } else {
            return "IP адрес " + ip + " не найден";
        }
    }
}