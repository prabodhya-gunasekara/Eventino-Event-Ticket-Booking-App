package com.example.eventino_event_ticket_booking_app

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.eventino_event_ticket_booking_app.adapter.EventAdapter
import com.example.eventino_event_ticket_booking_app.adapter.SliderAdapter
import com.example.eventino_event_ticket_booking_app.databinding.ActivityMainBinding
import com.example.eventino_event_ticket_booking_app.models.EventModel
import com.example.eventino_event_ticket_booking_app.models.SliderItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: FirebaseDatabase
    private val sliderHandler = Handler()
    private val sliderRunnable = Runnable { binding.viewpage2.currentItem = binding.viewpage2.currentItem + 1 }
    private lateinit var databaseReference: DatabaseReference

    private lateinit var newEventList: ArrayList<EventModel>
    private lateinit var upcomingEventList: ArrayList<EventModel>
    private lateinit var pastEventList: ArrayList<EventModel>

    private lateinit var newEventAdapter: EventAdapter
    private lateinit var upcomingEventAdapter: EventAdapter
    private lateinit var pastEventAdapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        initBanner()
        fetchNewEventData()
        fetchUpcomingEventData()
        fetchPastEventData()
    }

    private fun initBanner() {
        val myRef: DatabaseReference = database.getReference("Banners")
        binding.progressBarSlider.visibility = View.VISIBLE
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists: MutableList<SliderItem> = mutableListOf() // Explicitly use MutableList
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(SliderItem::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }

                binding.progressBarSlider.visibility = View.GONE
                banners(lists)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Failed to load banners", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun banners(lists: MutableList<SliderItem>) {
        binding.viewpage2.adapter = SliderAdapter(lists, binding.viewpage2)
        binding.viewpage2.offscreenPageLimit = 3
        binding.viewpage2.clipToPadding = false
        binding.viewpage2.clipChildren = false
        binding.viewpage2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
            addTransformer(ViewPager2.PageTransformer { page, position ->
                val r = 1 - Math.abs(position)
                page.scaleY = 0.85f + r * 0.15f
            })
        }
        binding.viewpage2.setPageTransformer(compositePageTransformer)
        binding.viewpage2.currentItem = 1
        binding.viewpage2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
            }
        })
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 2000)
    }

    private fun fetchNewEventData() {
        databaseReference = database.getReference("NewEvents")
        newEventList = ArrayList()
        binding.recycleViewNewEvent.layoutManager = LinearLayoutManager(this)
        newEventAdapter = EventAdapter(newEventList) // Correct initialization
        binding.recycleViewNewEvent.adapter = newEventAdapter

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.progressbarnewevent.visibility = View.GONE
                newEventList.clear()
                var count = 0
                for (childSnapshot in snapshot.children) {
                    if (count >= 3) break
                    val event = childSnapshot.getValue(EventModel::class.java)
                    event?.let { newEventList.add(it) }
                    count++
                }
                newEventAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Failed to load new events", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fetchUpcomingEventData() {
        databaseReference = database.getReference("UpcomingEvents")
        upcomingEventList = ArrayList()
        binding.recycleViewUpcomingEvent.layoutManager = LinearLayoutManager(this)
        upcomingEventAdapter = EventAdapter(upcomingEventList) // Correct initialization
        binding.recycleViewUpcomingEvent.adapter = upcomingEventAdapter

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.progressbarupcomingevent.visibility = View.GONE
                upcomingEventList.clear()
                var count = 0
                for (childSnapshot in snapshot.children) {
                    if (count >= 3) break
                    val event = childSnapshot.getValue(EventModel::class.java)
                    event?.let { upcomingEventList.add(it) }
                    count++
                }
                upcomingEventAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Failed to load upcoming events", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fetchPastEventData() {
        databaseReference = database.getReference("PastEvents")
        pastEventList = ArrayList()
        pastEventAdapter = EventAdapter(pastEventList) // Correct initialization
        binding.recycleViewPastEvent.layoutManager = LinearLayoutManager(this)
        binding.recycleViewPastEvent.adapter = pastEventAdapter

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.progressbarpastevent.visibility = View.GONE
                pastEventList.clear()
                var count = 0
                for (childSnapshot in snapshot.children) {
                    if (count >= 3) break
                    val event = childSnapshot.getValue(EventModel::class.java)
                    event?.let { pastEventList.add(it) }
                    count++
                }
                pastEventAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Failed to load past events", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
