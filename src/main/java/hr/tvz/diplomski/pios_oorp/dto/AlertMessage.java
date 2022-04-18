package hr.tvz.diplomski.pios_oorp.dto;

import hr.tvz.diplomski.pios_oorp.enumeration.AlertType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Alert message which is shown to {@link hr.tvz.diplomski.pios_oorp.domain.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertMessage {
    private String message;
    private AlertType alertType;
}
