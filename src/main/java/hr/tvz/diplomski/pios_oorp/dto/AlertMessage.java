package hr.tvz.diplomski.pios_oorp.dto;

import hr.tvz.diplomski.pios_oorp.enumeration.AlertType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertMessage {
    private String message;
    private AlertType alertType;
}
