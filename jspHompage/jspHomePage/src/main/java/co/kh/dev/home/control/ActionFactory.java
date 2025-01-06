package co.kh.dev.home.control;
import co.kh.dev.home.action.Action;
import co.kh.dev.home.action.alert.BoardListAlertAction;
import co.kh.dev.home.action.alert.BoardPageAlertAction;
import co.kh.dev.home.action.alert.MainPageAlertAction;
import co.kh.dev.home.action.alert.MyPageAlertAction;
import co.kh.dev.home.action.alert.NoticeListAlertAction;
import co.kh.dev.home.action.alert.NoticePageAlertAction;
import co.kh.dev.home.action.alert.PopLoginAlertAction;
import co.kh.dev.home.action.alert.ProductPageAlertAction;
import co.kh.dev.home.action.alert.ShopPageAlertAction;
import co.kh.dev.home.action.board.BoardCommentDeleteAction;
import co.kh.dev.home.action.board.BoardCommentInsertAction;
import co.kh.dev.home.action.board.BoardFindSelectAction;
import co.kh.dev.home.action.board.BoardInsertAction;
import co.kh.dev.home.action.board.BoardListDeleteAction;
import co.kh.dev.home.action.board.BoardListSelectAction;
import co.kh.dev.home.action.board.BoardListUpdateAction;
import co.kh.dev.home.action.board.BoardSelectAction;
import co.kh.dev.home.action.customer.CustomerDeleteAction;
import co.kh.dev.home.action.customer.CustomerIdDupCheckAction;
import co.kh.dev.home.action.customer.CustomerInsertAction;
import co.kh.dev.home.action.customer.CustomerInsertCheckAction;
import co.kh.dev.home.action.customer.CustomerLoginCheckAction;
import co.kh.dev.home.action.customer.CustomerLogoutAction;
import co.kh.dev.home.action.customer.CustomerPopLoginCheckAction;
import co.kh.dev.home.action.customer.CustomerUpdateAction;
import co.kh.dev.home.action.mainPage.mainPageSelectAction;
import co.kh.dev.home.action.notice.NoticeFindSelectAction;
import co.kh.dev.home.action.notice.NoticeInsertAction;
import co.kh.dev.home.action.notice.NoticeListDeleteAction;
import co.kh.dev.home.action.notice.NoticeListSelectAction;
import co.kh.dev.home.action.notice.NoticeListUpdateAction;
import co.kh.dev.home.action.notice.NoticeSelectAction;
import co.kh.dev.home.action.product.ProductFindSelectAction;
import co.kh.dev.home.action.product.ProductInsertAction;
import co.kh.dev.home.action.product.ProductListDeleteAction;
import co.kh.dev.home.action.product.ProductListUpdateAction;
import co.kh.dev.home.action.product.ProductSelectAction;
import co.kh.dev.home.action.shop.ShopCartDeleteAction;
import co.kh.dev.home.action.shop.ShopCartInsertAction;
import co.kh.dev.home.action.shop.ShopCartSelectAction;
import co.kh.dev.home.action.shop.ShopFindSelectAction;
import co.kh.dev.home.action.shop.ShopInsertAction;
import co.kh.dev.home.action.shop.ShopInsertPageSelectAction;
import co.kh.dev.home.action.shop.ShopListDeleteAction;
import co.kh.dev.home.action.shop.ShopListSelectAction;
import co.kh.dev.home.action.shop.ShopSelectAction;


public class ActionFactory {
	private static ActionFactory af = null;

	public static synchronized ActionFactory getInstance() {
		if (af == null) {
			af = new ActionFactory();
		}
		return af;
	}

	private ActionFactory() {
		super();
	}

	public Action getAction(String cmd) {
		Action action = null;

		switch (cmd) {
		case "popLoginAlert": 
			action = new PopLoginAlertAction();
			break;
		case "shopPageAlert": 
			action = new ShopPageAlertAction();
			break;
		case "mainPageAlert": 
			action = new MainPageAlertAction();
			break;
		case "myPageAlert": 
			action = new MyPageAlertAction();
			break;
		case "boardListAlert": 
			action = new BoardListAlertAction();
			break;
		case "boardPageAlert": 
			action = new BoardPageAlertAction();
			break;
		case "noticePageAlert": 
			action = new NoticePageAlertAction();
			break;
		case "noticeListAlert": 
			action = new NoticeListAlertAction();
			break;
		case "productPageAlert": 
			action = new ProductPageAlertAction();
			break;
		case "boardSelect": 
			action = new BoardSelectAction();
			break;
		case "boardInsert": 
			action = new BoardInsertAction();
			break;
		case "boardFindSelect": 
			action = new BoardFindSelectAction();
			break;
		case "boardListSelect": 
			action = new BoardListSelectAction();
			break;
		case "boardListDelete": 
			action = new BoardListDeleteAction();
			break;
		case "boardListUpdate": 
			action = new BoardListUpdateAction();
			break;
		case "boardCommentInsert": 
			action = new BoardCommentInsertAction();
			break;
		case "boardCommentDelete": 
			action = new BoardCommentDeleteAction();
			break;
		case "customerUpdate": 
			action = new CustomerUpdateAction();
			break;
		case "customerLoginCheck": 
			action = new CustomerLoginCheckAction();
			break;
		case "customerPopLoginCheck": 
			action = new CustomerPopLoginCheckAction();
			break;
		case "customerDelete": 
			action = new CustomerDeleteAction();
			break;
		case "customerLogout": 
			action = new CustomerLogoutAction();
			break;
		case "customerIdDupCheck": 
			action = new CustomerIdDupCheckAction();
			break;
		case "customerInsert": 
			action = new CustomerInsertAction();
			break;
		case "customerInsertCheck": 
			action = new CustomerInsertCheckAction();
			break;
		case "mainPageSelect": 
			action = new mainPageSelectAction();
			break;

		case "noticeSelect": 
			action = new NoticeSelectAction();
			break;
		case "noticeInsert": 
			action = new NoticeInsertAction();
			break;
		case "noticeFindSelect": 
			action = new NoticeFindSelectAction();
			break;
		case "noticeListSelect": 
			action = new NoticeListSelectAction();
			break;
		case "noticeListDelete": 
			action = new NoticeListDeleteAction();
			break;
		case "noticeListUpdate": 
			action = new NoticeListUpdateAction();
			break;
			
		case "productSelect": 
			action = new ProductSelectAction();
			break;
		case "productInsert": 
			action = new ProductInsertAction();
			break;
		case "productFindSelect": 
			action = new ProductFindSelectAction();
			break;
		case "productListDelete": 
			action = new ProductListDeleteAction();
			break;
		case "productListUpdate": 
			action = new ProductListUpdateAction();
			break;
		case "shopSelect": 
			action = new ShopSelectAction();
			break;
		case "shopInsert": 
			action = new ShopInsertAction();
			break;
		case "shopInsertPageSelect": 
			action = new ShopInsertPageSelectAction();
			break;
		case "shopListSelect": 
			action = new ShopListSelectAction();
			break;
		case "shopListDelete": 
			action = new ShopListDeleteAction();
			break;
		case "shopCartInsert": 
			action = new ShopCartInsertAction();
			break;
		case "shopCartSelect": 
			action = new ShopCartSelectAction();
			break;
		case "shopCartDelete": 
			action = new ShopCartDeleteAction();
			break;
		case "shopFindSelect": 
			action = new ShopFindSelectAction();
			break;
		default: 
			action = null;
			break;
		}
		return action;
	}

}
