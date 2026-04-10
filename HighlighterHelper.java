public class HighlighterHelper {
    public static SpannableString applyHighlights(String text, List<HighlightRange> ranges) {
        SpannableString spannable = new SpannableString(text);
        for (HighlightRange range : ranges) {
            spannable.setSpan(
                new BackgroundColorSpan(range.color),
                range.start,
                range.end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );
        }
        return spannable;
    }
}
