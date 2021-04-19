package br.com.gava.api.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.aspectj.util.LangUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pagination {

  @Builder.Default
  private String search = null;

  private int page;
  @Builder.Default
  private int size = 1;
  @Builder.Default
  private String order = "name";
  @Builder.Default
  private String sort = "asc";

  public void setPage(int page) {
    if(page > 0) {
      this.page = page - 1;
      return;
    }
    this.page = 0;
  }

  public PageRequest pageRequest() {
     return PageRequest.of(
      this.getPage(),
      this.getSize(),
      Sort.Direction.valueOf(this.getSort().toUpperCase()),
      this.getOrder()
    );
  }

  public int getPageUrl() {
    return this.page + 1;
  }

  public String toString() {
    String toSearch = StringUtils.hasText(this.getSearch())
      ? "search=" + this.getSearch() + "&"
      : "";

    return "?" + toSearch
      + "page=" + this.getPageUrl()
      + "&size=" + this.getSize()
      + "&order=" + this.getOrder()
      + "&sort=" + this.getSort(); }
}
