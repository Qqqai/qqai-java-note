package qqai.task.schedule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;

/**
 * 定时器配置 by ai q 2021/3/8 15:32
 */
@Configuration
@EnableScheduling // 开启定时任务
public class DynamicScheduleTask implements SchedulingConfigurer {

  @Value("${time.task.cron}")
  private String cron;

  private static final Logger log = LoggerFactory.getLogger(DynamicScheduleTask.class);

  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    taskRegistrar.addCronTask(new CronTask(
        () -> log.info("run task time {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))), cron));

    taskRegistrar.addTriggerTask(new TriggerTask(() -> {
      log.info("run task time {}",
          LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    },
        triggerContext -> {
          Calendar before = Calendar.getInstance();
          before.add(Calendar.SECOND, 10);
          return before.getTime();
        }));
  }

}
