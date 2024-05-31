package domain;

public class MemberOrderStat {
	private int processingCnt, shippedOutCnt, inDeliveryCnt,  deliveredCnt;
	
	
	public void addCount(int status, int count) {
		if(status == 0) {
			processingCnt += count;
		}
		else if(status == 1) {
			shippedOutCnt+= count;
		}
		else if(status == 2) {
			inDeliveryCnt+= count;
		}
		if(status == 3) {
			deliveredCnt+= count;
		}
	}
	

	public int getProcessingCnt() {
		return processingCnt;
	}

	public int getShippedOutCnt() {
		return shippedOutCnt;
	}

	public int getInDeliveryCnt() {
		return inDeliveryCnt;
	}

	public int getDeliveredCnt() {
		return deliveredCnt;
	}
	
	
	
	public int getTotalCnt() {
		return processingCnt + shippedOutCnt + inDeliveryCnt + deliveredCnt;
	}
	
	
	

}
