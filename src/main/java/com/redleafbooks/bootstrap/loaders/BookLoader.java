package com.redleafbooks.bootstrap.loaders;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class BookLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*private UserRepository bookRepository;
	/*private BrowseItemRepository browseItemRepository;

	@Autowired
	public void setBookRepository(UserRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Autowired
	public void setBrowseItemRepository(BrowseItemRepository browseItemRepository) {
		this.browseItemRepository = browseItemRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Fairy generator = Fairy.create();
		generateNewBooks(generator, 200);
	}

	private void generateNewBooks(Fairy generator, int generateAmount) {
		for (int i = 0; i < generateAmount; i++) {
			generateNewBook(generator);
		}
	}

	private void generateNewBook(Fairy generator) {
		Book book = new Book();
		book.setTitle(generateTitle(generator, 80));
		book.setDescription(generator.textProducer().limitedTo(500).latinSentence().toString());

		double price = generateDouble(1, 15) ;
		book.setPrice(new BigDecimal(price));

		if(ThreadLocalRandom.current().nextInt(0, 10 + 1) <= 3){ //30% of a promo
			book.setPrevPrice(new BigDecimal(roundUp(price - (price / 100 * ThreadLocalRandom.current().nextInt(10, 40 + 1)))));
		}

		this.bookRepository.save(book);

		BrowseItem browseItem = new BrowseItem();
		browseItem.setTitle(book.getTitle());
		browseItem.setDescription(StringUtils.left(book.getTitle(), 300));
		browseItem.setRating(ThreadLocalRandom.current().nextInt(0, 5 + 1));
		browseItem.setPrice(Double.toString(book.getPrice().doubleValue()));
		if(null != book.getPrevPrice()){
			browseItem.setPrevPrice(Double.toString(book.getPrevPrice().doubleValue()));
		}

		this.browseItemRepository.save(browseItem);
	}

	private String generateTitle(Fairy generator, int limit) {
		return StringUtils.capitalize(generator.textProducer().limitedTo(limit).latinSentence().replaceAll("\\.", "").replaceAll(",", "").toLowerCase());
	}

	private double generateDouble(int min, int max) {
		double random = ThreadLocalRandom.current().nextDouble(min, max+ 1d);
		return roundUp(random);
	}

	private double roundUp(double random) {
		BigDecimal bd = new BigDecimal(random);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}*/
}
