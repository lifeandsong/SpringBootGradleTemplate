package org.swmaestro.demo.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Sample Batch
 * 
 * @since	2022-06-29
 * @author	ywkim
 */
@Component
@Slf4j
public class SampleBatch extends BaseBatch {

	public SampleBatch() {
		this.batchName = "Sample Batch";
	}

	/**
	 * cron로 지정된 시간대에 항상 실행한다.
	 */
	@Scheduled(cron = "0 */15 * ? * *", zone = "Asia/Seoul")
	public void runBatch() {
		logStartedAt();

		// 여기에 실행시킬 기능을 추가한다.
		log.info("Sample Batch has been executed.");

		logEndedAt();
	}

}
