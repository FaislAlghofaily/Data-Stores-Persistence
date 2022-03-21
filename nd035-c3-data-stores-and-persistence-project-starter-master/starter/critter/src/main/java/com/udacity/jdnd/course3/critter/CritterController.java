package com.udacity.jdnd.course3.critter;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.bytebuddy.asm.Advice.Local;

/**
 * Dummy controller class to verify installation success. Do not use for
 * your project work.
 */
@RestController
public class CritterController {

    @GetMapping("/test")
    public String test(){
    	LocalDate localDate=LocalDate.now();
    	DayOfWeek dayOfWeek=localDate.getDayOfWeek();
        return dayOfWeek.toString();
    }
}
