package ch.yth2021.charjar.discord.module.rolemanagment;

import lombok.Builder;
import lombok.Data;

import java.awt.*;

@Builder
@Data
public class CustomRole {
    private String name;
    private Color color;
    private Integer minBalance;
    private Integer maxBalance;
}
