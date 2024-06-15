package pager;

import java.util.List;

public class Pager<T> {
	
	private List<T> content;
	private int pageNumber;
	private int perPageSize;
	private int totalElementCount;
	private int totalPage;
	
	public Pager(List<T> content, int pageNumber, int perPageSize, int totalElementCount) {
		this.content = content;
		this.pageNumber = pageNumber;
		this.perPageSize = perPageSize;
		this.totalElementCount = totalElementCount;
		this.totalPage =  (int) Math.ceil((double)totalElementCount/perPageSize);
	}

	public List<T> getContent() {
		return content;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public int getPerPageSize() {
		return perPageSize;
	}

	public int getTotalElementCount() {
		return totalElementCount;
	}

	public int getTotalPage() {
		return totalPage;
	}
	
	
	private String makeUrl(String baseUrl, String... parameters) {
		int parameterSize = parameters.length;
		
		StringBuilder sb = new StringBuilder();
		sb.append(baseUrl).append("?");
		
		for(int i=0; i<parameterSize; i++) {
			sb.append(parameters[i]);
			sb.append("&");
		}
		
		return sb.toString();
	
	}
	
	public String makePageBar(String baseUrl, String... parameters) {
		
		int blockSize = 10;
		int loop = 1;
		int pageNo = ((pageNumber - 1)/blockSize) * blockSize + 1;
		String urlWithQuery = makeUrl(baseUrl, parameters);
		
		String pageBar = "<li class='page-item'><a class='page-link' href='"+urlWithQuery+ "page=1'>[맨처음]</a></li>";
		
		
		if(pageNo != 1) {
			pageBar += "<li class='page-item'><a class='page-link' href='"+ urlWithQuery + "page="+ (pageNo - 1) +"'>[이전]</a></li>";
		}
		
		//맨처음 맨마지막 만들기
		
		while( !(loop > blockSize || pageNo > totalPage) ) {
			
			//1 2 3 4 5 6 7  8 9 10
			//pageBar += "<li class='page-item'><a class='page-link' href='memberList.up?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";
			
			if(pageNo == pageNumber) {
				
				pageBar += "<li class='page-item active'><a class='page-link' href='#'>"+pageNo+"</a></li>";
			}
			else {
				
				pageBar += "<li class='page-item'><a class='page-link' href='"+ urlWithQuery + "page="+ pageNo + "'>"+pageNo+"</a></li>";
			}
			
			
			loop ++; 
			
			
			// 1 2 3 4 5 6 7  8 9 10
			// 11 12 13 14 15 16 17 18 19 20
			pageNo ++;
			
		}
		
		pageBar += "<li class='page-item'><a class='page-link' href='" + urlWithQuery + "page="+ (totalPage) +"'>[맨마지막]</a></li>";
		//다음 마지막 만들기
		if(pageNo <= totalPage) {
			pageBar += "<li class='page-item'><a class='page-link' href='"+ urlWithQuery+"page="+ (totalPage + 1)+"'>[다음]</a></li>";
		}
		
		return pageBar;
	}
	

}
